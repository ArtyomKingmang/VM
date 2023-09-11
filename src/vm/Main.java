package vm;

import static vm.Bytecode.*;
import static vm.Bytecode.HALT;

public class Main {
    static final int[] add = {
            CONST, 2,
            CONST, 3,
            ADD,
            HALT
    };
    public static void main(String[] args) throws Exception {
        VM vm = new VM(add, 0, 0);
        vm.trace = true;
        vm.exec();
    }
}
