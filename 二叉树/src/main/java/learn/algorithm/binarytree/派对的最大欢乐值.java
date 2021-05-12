package learn.algorithm.binarytree;

import java.util.List;

public class 派对的最大欢乐值 {

    class Employee {
        int happy;
        List<Employee> subordinates;
    }

    class ReturnType {
        int happyWithBoss;
        int happyWithoutBoss;


        public ReturnType(int happyWithBoss, int happyWithoutBoss) {
            this.happyWithBoss = happyWithBoss;
            this.happyWithoutBoss = happyWithoutBoss;
        }
    }

    public int maxHappyOfParty(Employee boss) {
        if (boss == null) return 0;
        ReturnType res = process(boss);
        return Math.max(res.happyWithBoss, res.happyWithoutBoss);
    }

    public ReturnType process(Employee employee) {
        int happyWithBoss = employee.happy;
        int happyWithoutBoss = 0;
        if (employee.subordinates != null) {
            for (Employee subordinate : employee.subordinates) {
                ReturnType sub = process(subordinate);
                happyWithBoss += sub.happyWithoutBoss;
                happyWithoutBoss += Math.max(sub.happyWithBoss, sub.happyWithoutBoss);
            }
        }
        return new ReturnType(happyWithBoss, happyWithoutBoss);
    }

}
