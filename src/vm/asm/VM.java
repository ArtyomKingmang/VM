package vm.asm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VM {
    public static final int TRUE = 1;
    public static final int FALSE = 0;

    public static final int STACK_SIZE = 100;

    private int[] code;
    private int[] stack;
    private int[] globals;
    private Set<Integer> usedGlobalIndexes = new HashSet<Integer>();

    private int ip;
    private int sp;
    private int fp;

    public boolean trace = false;
    private int lineNum = 0;

    public VM(int[] code, int ipmain, int datasize) {
        this.code = code;
        this.stack = new int[STACK_SIZE];
        this.globals = new int[datasize];

        this.ip = ipmain;
        this.sp = -1;
        this.fp = 0;
    }

    public void exec() throws Exception {
        loop:
        while (ip < code.length) {
            if (trace) disassemble();
            switch (Bytecode.values()[nextCode()]) {
                case HALT:
                    break loop;
                case CONST:
                    pushStack(nextCode());
                    break;
                case ADD:
                    pushStack(popStack() + popStack());
                    break;
                case SUB:
                    int last = popStack();
                    int first = popStack();
                    pushStack(first - last);
                    break;
                case MUL:
                    pushStack(popStack() * popStack());
                    break;

            }
        }

        if (trace && ip < code.length) disassemble();
        if (trace && !usedGlobalIndexes.isEmpty()) System.err.println(globalsToString());
    }

    private int nextCode() {
        return code[ip++];
    }

    private int currCode() {
        return code[ip];
    }

    private void pushStack(int value) {
        stack[++sp] = value;
    }

    private int popStack() {
        return stack[sp--];
    }

    private void disassemble() {
        Bytecode instr = Bytecode.values()[currCode()];
        StringBuilder buf = new StringBuilder(instr.getName());
        for (int i = 0; i < instr.getNargs(); i++) {
            buf.append(", ").append(code[ip + i + 1]);
        }

        System.err.printf("%4d: %04d  %-20s %s\n", lineNum++, ip, buf.toString(), stackToString());
    }

    private String stackToString() {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i <= sp; i++) {
            list.add(stack[i]);
        }

        return "stack: " + list;
    }

    private String globalsToString() {
        List<String> list = new ArrayList<String>();
        for (Integer index : usedGlobalIndexes) {
            list.add(index.toString() + " => " + globals[index]);
        }

        return "Global: " + list;
    }
}