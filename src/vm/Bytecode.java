package vm;

public class Bytecode {
    public static final int HALT = 0;
    public static final int CONST = 1;
    public static final int ADD = 2;
    public static final int SUB = 3;
    public static final int MUL = 4;

    static class Instruction {
        String name;
        int nargs;

        public Instruction(String name) {
            this(name, 0);
        }

        public Instruction(String name, int nargs) {
            this.name = name;
            this.nargs = nargs;
        }
    }

    static Instruction[] instructions = {
            new Instruction("halt"),
            new Instruction("const", 1),
            new Instruction("add"),
            new Instruction("sub"),
            new Instruction("mul"),
    };
}
