package com.dsa.practice.demo.Scaler.LLd;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class BattleShipGame {

    /*
     * Battleship Game — Low-Level Design (LLD) + Full Java Implementation
     * Language: Java 17+
     * Run: javac BattleshipGame.java && java BattleshipGame
     *
     * ----------------------------
     * LLD OVERVIEW
     * ----------------------------
     * Entities & Responsibilities
     *
     * 1) Position (value object)
     *    - row, col; bounds-safe factory; equals/hashCode.
     *
     * 2) Orientation (enum)
     *    - HORIZONTAL, VERTICAL.
     *
     * 3) CellState (enum)
     *    - UNKNOWN, MISS, HIT, SHIP (internal-only for rendering own board).
     *
     * 4) ShipType (enum)
     *    - CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2).
     *
     * 5) Ship
     *    - type, origin (top-left), orientation, positions, hits (Set<Position>).
     *    - Methods: occupies(Position), isSunk(), cells().
     *
     * 6) Board
     *    - size (N x N), ships (List<Ship>), shots (Map<Position, CellState>),
     *      occupied (Set<Position>) for quick collision checks.
     *    - Methods: canPlace(Ship), place(Ship), randomPlaceAll(), shoot(Position): ShotOutcome,
     *      allShipsSunk(), renderOwn(), renderFogOfWar().
     *
     * 7) ShotOutcome (enum + Result)
     *    - Type: MISS, HIT, SUNK, REPEAT, INVALID, WIN.
     *    - Result object holds: type, optional ship sunk.
     *
     * 8) Player (abstract)
     *    - name, Board own, Set<Position> fired.
     *    - selectTarget(Board opponent) -> Position (implemented by subclasses),
     *      notify(ShotResult) for basic A.I. feedback (optional).
     *
     * 9) HumanPlayer extends Player
     *    - Reads coordinates from console as: A-J for rows, 1-10 for columns; supports commands
     *      like "help", "show", "quit".
     *
     * 10) AIPlayer extends Player
     *    - Simple "hunt & target" strategy:
     *      - Hunt: fire at random unseen cells with checkerboard parity.
     *      - Target: if a hit is recorded, probe neighbors to sink ship.
     *
     * 11) GameEngine
     *    - Turn loop, alternates players until WIN.
     *    - Setup: random placement for both, with human option to view own board.
     *
     * 12) BattleshipGame (public, with main)
     *    - CLI entrypoint, configuration (board size, fleet), starts GameEngine.
     *
     * Design Notes
     * - Immutability for Position; deterministic equals/hashCode.
     * - Separation of concerns: Board doesn't know who fired, only applies shots.
     * - Rendering methods return strings; UI (console) prints them.
     * - Easy extensibility: add new ShipType, change board size, plug different AI.
     *
     * ----------------------------
     * CONTROLS (Console)
     * ----------------------------
     * - Enter coordinates like: A5, J10, c7 (case-insensitive).
     * - Commands: "show" (prints your board), "help" (help), "quit" (exit).
     */

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("\n=== Battleship (Java Console) ===\n");
        int size = 10; // standard 10x10
        List<ShipType> fleet = List.of(
                ShipType.CARRIER,
                ShipType.BATTLESHIP,
                ShipType.CRUISER,
                ShipType.SUBMARINE,
                ShipType.DESTROYER
        );

        Board humanBoard = new Board(size, fleet);
        Board aiBoard = new Board(size, fleet);

        humanBoard.randomPlaceAll();
        aiBoard.randomPlaceAll();

        Player human = new HumanPlayer("You", humanBoard, in);
        Player ai = new AIPlayer("Computer", aiBoard);

        GameEngine engine = new GameEngine(human, ai, in);
        engine.run();
    }
}

// ----------------------------
// Value Objects & Enums
// ----------------------------

final class Position {
    final int row; // 0-indexed
    final int col; // 0-indexed

    Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    static Optional<Position> parse(String token, int size) {
        token = token.trim().toUpperCase(Locale.ROOT);
        if (token.length() < 2 || token.length() > 3) return Optional.empty();
        char r = token.charAt(0);
        if (r < 'A' || r >= 'A' + size) return Optional.empty();
        String cpart = token.substring(1);
        try {
            int c = Integer.parseInt(cpart);
            if (c < 1 || c > size) return Optional.empty();
            return Optional.of(new Position(r - 'A', c - 1));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position p = (Position) o;
        return row == p.row && col == p.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }

    @Override
    public String toString() {
        return "" + (char) ('A' + row) + (col + 1);
    }
}

enum Orientation {HORIZONTAL, VERTICAL}

enum CellState {UNKNOWN, MISS, HIT, SHIP}

enum ShipType {
    CARRIER(5), BATTLESHIP(4), CRUISER(3), SUBMARINE(3), DESTROYER(2);
    public final int length;

    ShipType(int len) {
        this.length = len;
    }
}

enum ShotType {MISS, HIT, SUNK, REPEAT, INVALID, WIN}

final class ShotResult {
    final ShotType type;
    final Ship sunk;
    final Position at;

    ShotResult(ShotType type, Position at) {
        this(type, at, null);
    }

    ShotResult(ShotType type, Position at, Ship sunk) {
        this.type = type;
        this.at = at;
        this.sunk = sunk;
    }
}

// ----------------------------
// Ship & Board
// ----------------------------

final class Ship {
    final ShipType type;
    final Position origin; // top-left cell
    final Orientation orientation;
    private final Set<Position> hits = new HashSet<>();

    Ship(ShipType type, Position origin, Orientation orientation) {
        this.type = type;
        this.origin = origin;
        this.orientation = orientation;
    }

    List<Position> cells() {
        List<Position> list = new ArrayList<>(type.length);
        for (int i = 0; i < type.length; i++) {
            int r = origin.row + (orientation == Orientation.VERTICAL ? i : 0);
            int c = origin.col + (orientation == Orientation.HORIZONTAL ? i : 0);
            list.add(new Position(r, c));
        }
        return list;
    }

    boolean occupies(Position p) {
        return cells().contains(p);
    }

    void hit(Position p) {
        if (occupies(p)) hits.add(p);
    }

    boolean isSunk() {
        return hits.size() >= type.length;
    }
}

final class Board {
    final int size;
    final List<ShipType> fleet;
    final List<Ship> ships = new ArrayList<>();
    final Map<Position, CellState> shots = new HashMap<>(); // opponent's shots at me
    final Set<Position> occupied = new HashSet<>();

    Board(int size, List<ShipType> fleet) {
        this.size = size;
        this.fleet = new ArrayList<>(fleet);
    }

    boolean inBounds(Position p) {
        return p.row >= 0 && p.row < size && p.col >= 0 && p.col < size;
    }

    boolean canPlace(Ship candidate) {
        for (Position p : candidate.cells()) {
            if (!inBounds(p) || occupied.contains(p)) return false;
        }
        return true;
    }

    void place(Ship s) {
        if (!canPlace(s)) throw new IllegalArgumentException("Invalid placement");
        ships.add(s);
        occupied.addAll(s.cells());
    }

    void randomPlaceAll() {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        for (ShipType t : fleet) {
            boolean placed = false;
            int guard = 0;
            while (!placed) {
                if (guard++ > 10_000) throw new IllegalStateException("Failed to place ships");
                Orientation o = rnd.nextBoolean() ? Orientation.HORIZONTAL : Orientation.VERTICAL;
                int r = rnd.nextInt(size);
                int c = rnd.nextInt(size);
                Position origin = new Position(r, c);
                Ship s = new Ship(t, origin, o);
                // Adjust origin if ship would overflow
                List<Position> cells = s.cells();
                int dr = o == Orientation.VERTICAL ? t.length - 1 : 0;
                int dc = o == Orientation.HORIZONTAL ? t.length - 1 : 0;
                if (r + dr >= size || c + dc >= size) {
                    // shift origin so tail fits within bounds
                    r = Math.min(r, size - 1 - dr);
                    c = Math.min(c, size - 1 - dc);
                    s = new Ship(t, new Position(r, c), o);
                    cells = s.cells();
                }
                if (canPlace(s)) {
                    place(s);
                    placed = true;
                }
            }
        }
    }

    ShotResult shoot(Position p) {
        if (!inBounds(p)) return new ShotResult(ShotType.INVALID, p);
        CellState prev = shots.getOrDefault(p, CellState.UNKNOWN);
        if (prev == CellState.MISS || prev == CellState.HIT) return new ShotResult(ShotType.REPEAT, p);

        for (Ship s : ships) {
            if (s.occupies(p)) {
                s.hit(p);
                shots.put(p, CellState.HIT);
                if (s.isSunk()) {
                    if (allShipsSunk()) return new ShotResult(ShotType.WIN, p, s);
                    return new ShotResult(ShotType.SUNK, p, s);
                }
                return new ShotResult(ShotType.HIT, p);
            }
        }
        shots.put(p, CellState.MISS);
        return new ShotResult(ShotType.MISS, p);
    }

    boolean allShipsSunk() {
        for (Ship s : ships) if (!s.isSunk()) return false;
        return true;
    }

    String renderOwn() {
        // Shows ships + hits/misses
        StringBuilder sb = new StringBuilder();
        header(sb);
        for (int r = 0; r < size; r++) {
            sb.append((char) ('A' + r)).append(" ");
            for (int c = 0; c < size; c++) {
                Position p = new Position(r, c);
                char ch;
                if (occupied.contains(p)) {
                    CellState st = shots.getOrDefault(p, CellState.UNKNOWN);
                    if (st == CellState.HIT) ch = 'X';
                    else ch = 'O'; // ship present but not hit
                } else {
                    CellState st = shots.getOrDefault(p, CellState.UNKNOWN);
                    if (st == CellState.MISS) ch = '~';
                    else ch = '.';
                }
                sb.append(ch).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    String renderFogOfWar() {
        // Opponent view: only show hits/misses, not ship positions
        StringBuilder sb = new StringBuilder();
        header(sb);
        for (int r = 0; r < size; r++) {
            sb.append((char) ('A' + r)).append(" ");
            for (int c = 0; c < size; c++) {
                Position p = new Position(r, c);
                CellState st = shots.getOrDefault(p, CellState.UNKNOWN);
                char ch;
                if (st == CellState.MISS) ch = '~';
                else if (st == CellState.HIT) ch = 'X';
                else ch = '.';
                sb.append(ch).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    private void header(StringBuilder sb) {
        sb.append("   ");
        for (int c = 1; c <= size; c++) {
            if (c < 10) sb.append(c).append(' ');
            else sb.append(c);
            if (c < size) sb.append(' ');
        }
        sb.append('\n');
    }
}

// ----------------------------
// Players
// ----------------------------

abstract class Player {
    final String name;
    final Board own;
    final Set<Position> fired = new HashSet<>();

    Player(String name, Board own) {
        this.name = name;
        this.own = own;
    }

    abstract Optional<Position> selectTarget(Board opponent);

    void notifyResult(ShotResult r) { /* default: no-op */ }
}

final class HumanPlayer extends Player {
    private final Scanner in;

    HumanPlayer(String name, Board own, Scanner in) {
        super(name, own);
        this.in = in;
    }

    @Override
    Optional<Position> selectTarget(Board opponent) {
        while (true) {
            System.out.print("Enter target (e.g., A5) or 'help'/'show'/'quit': ");
            String line = in.nextLine().trim();
            if (line.equalsIgnoreCase("quit")) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
            if (line.equalsIgnoreCase("help")) {
                System.out.println("- Coordinates: Row(A-J) + Col(1-10), e.g., B7\n- '~' = miss, 'X' = hit, 'O' = your ship, '.' = unknown\n- 'show' to view your board\n");
                continue;
            }
            if (line.equalsIgnoreCase("show")) {
                System.out.println("\nYour Board:\n" + own.renderOwn());
                continue;
            }
            Optional<Position> p = Position.parse(line, opponent.size);
            if (p.isEmpty()) {
                System.out.println("Invalid input. Try like C9.");
                continue;
            }
            if (fired.contains(p.get())) {
                System.out.println("You already fired at " + p.get() + ". Try a new cell.");
                continue;
            }
            fired.add(p.get());
            return p;
        }
    }
}

final class AIPlayer extends Player {
    private final Deque<Position> targets = new ArrayDeque<>();
    private final Set<Position> tried = new HashSet<>();

    AIPlayer(String name, Board own) {
        super(name, own);
    }

    @Override
    Optional<Position> selectTarget(Board opponent) {
        // Use queued targets first (from previous hits), else hunt with parity
        Position next = null;
        while (!targets.isEmpty()) {
            Position p = targets.pollFirst();
            if (!tried.contains(p) && opponent.inBounds(p)) {
                next = p;
                break;
            }
        }
        if (next == null) {
            next = hunt(opponent);
        }
        tried.add(next);
        fired.add(next);
        return Optional.of(next);
    }

    private Position hunt(Board opponent) {
        ThreadLocalRandom rnd = ThreadLocalRandom.current();
        int size = opponent.size;
        // Checkerboard parity to improve odds for length>=2 ships
        int attempts = 0;
        while (true) {
            attempts++;
            int r = rnd.nextInt(size);
            int c = rnd.nextInt(size);
            if ((r + c) % 2 != 0 && attempts < 500) continue; // enforce parity mostly
            Position p = new Position(r, c);
            if (!tried.contains(p)) return p;
        }
    }

    @Override
    void notifyResult(ShotResult r) {
        if (r.type == ShotType.HIT || r.type == ShotType.SUNK) {
            // add neighbors to targets to pursue ship
            int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
            for (int[] d : dirs) {
                Position n = new Position(r.at.row + d[0], r.at.col + d[1]);
                targets.addLast(n);
            }
        }
        // If WIN or SUNK, no special handling needed beyond default
    }
}

// ----------------------------
// Game Engine
// ----------------------------

final class GameEngine {
    private final Player p1; // human
    private final Player p2; // ai
    private final Scanner in;

    GameEngine(Player p1, Player p2, Scanner in) {
        this.p1 = p1;
        this.p2 = p2;
        this.in = in;
    }

    void run() {
        System.out.println("Your fleet is placed. Type 'show' anytime to view it. Good luck!\n");
        boolean humanTurn = true;
        Board human = p1.own, ai = p2.own;

        while (true) {
            if (humanTurn) {
                System.out.println("Enemy Board (fog of war):\n" + ai.renderFogOfWar());
                Optional<Position> opt = p1.selectTarget(ai);
                Position shot = opt.orElseThrow();
                ShotResult res = ai.shoot(shot);
                p2.notifyResult(res); // AI learns about what was hit on its own board
                printShotOutcome(p1.name, res);
                if (res.type == ShotType.WIN) {
                    System.out.println("\nYou win! GG.");
                    break;
                }
                humanTurn = res.type == ShotType.HIT || res.type == ShotType.SUNK; // extra turn on hit
            } else {
                // AI move
                Position shot = p2.selectTarget(human).orElseThrow();
                System.out.println("Computer fires at " + shot + "...");
                ShotResult res = human.shoot(shot);
                p1.notifyResult(res); // human could use for UI, not required
                printShotOutcome(p2.name, res);
                if (res.type == ShotType.WIN) {
                    System.out.println("\nComputer wins. Better luck next time!");
                    break;
                }
                humanTurn = !(res.type == ShotType.HIT || res.type == ShotType.SUNK); // AI keeps turn on hit
            }
        }
    }

    private void printShotOutcome(String shooter, ShotResult res) {
        switch (res.type) {
            case INVALID -> System.out.println(shooter + " shot " + res.at + " is INVALID.");
            case REPEAT -> System.out.println(shooter + " already targeted " + res.at + ".");
            case MISS -> System.out.println(shooter + " fires at " + res.at + ": MISS.");
            case HIT -> System.out.println(shooter + " fires at " + res.at + ": HIT!");
            case SUNK -> System.out.println(shooter + " sinks a " + res.sunk.type + " at " + res.at + "!");
            case WIN -> System.out.println(shooter + " completes the final blow at " + res.at + "! All ships sunk.");
        }
    }
}

