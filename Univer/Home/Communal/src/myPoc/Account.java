package myPoc;

import myException.AccountException;

public class Account implements Comparable <Account> {
    private FullName fullName;                                      //ФИО                       36
    private int debt_count;                                         //Сумма платежа (долг)      8
    private int paid_count;                                         //Внесённая сумма оплаты    8
    private String pay_type;                                        //Вид платежа               30


    public Account(Account element) throws AccountException {
        if (element.debt_count < 0 || element.paid_count < 0)
            throw new AccountException("Ввод отрицательных чисел не допустим!");
        this.fullName = new FullName(element.fullName);
        this.debt_count = element.debt_count;
        this.paid_count = element.paid_count;
        this.pay_type = element.pay_type.trim();
    }

    public Account() {
        this.fullName = new FullName();
        this.debt_count = 0;
        this.paid_count = 0;
        this.pay_type = "";
    }

    public Account(String s1, String s2, String s3, int i1, int i2, String s4) throws AccountException {
        if (i1 < 0 || i2 < 0) throw new AccountException("Ввод отрицательных чисел не допустим!");
        this.fullName = new FullName(s1, s2, s3);
        this.debt_count = i1;
        this.paid_count = i2;
        this.pay_type = s4.trim();
    }

    public Account(FullName fullName, int i1, int i2, String s4) throws AccountException {
        if (i1 < 0 || i2 < 0) throw new AccountException("Ввод отрицательных чисел не допустим!");
        this.fullName = new FullName(fullName);
        this.debt_count = i1;
        this.paid_count = i2;
        this.pay_type = s4.trim();
    }

    public boolean is_debtor() {                                    //Должник?
        if (debt_count > paid_count) return true;
        return false;
    }

    public boolean obligation(int min, int max) {                   //Входит ли человек в диапозон !ДОБАВИТЬ ПРОВЕРКУ!
        int t = debt_count - paid_count;
        if (t >= min && t <= max) return true;
        return false;
    }

    public void upCase() {
        fullName.upCase();
        if (!pay_type.isEmpty())
            pay_type = pay_type.toLowerCase();
    }

    @Override
    public int hashCode() {
        return fullName.hashCode() + pay_type.hashCode() + paid_count + debt_count;
    }


    @Override
    public String toString() {
        return String.format("%s %-8d %-8d %-30s", fullName, debt_count, paid_count, pay_type);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        if (this == object) return true;
        if (!(object instanceof Account)) return false;
        Account z = (Account) object;
        if (!this.fullName.equals(z.fullName)) return false;
        if (!this.pay_type.equals(z.pay_type)) return false;
        if (this.debt_count != z.debt_count) return false;
        if (this.paid_count != z.paid_count) return false;
        return true;
    }

    @Override
    public int compareTo(Account account) {
        return this.fullName.compareTo(account.fullName);
    }

    public FullName getFullName() {
        return fullName;
    }

    public void setFullName(FullName fullName) {
        this.fullName = fullName;
    }

    public String getPay_type() {
        return pay_type;
    }

    public void setPay_type(String pay_type) {
        this.pay_type = pay_type.trim();
    }

    public int getPaid_count() {
        return paid_count;
    }

    public void setPaid_count(int paid_count) throws AccountException {
        if (paid_count < 0) throw new AccountException("Ввод отрицательных чисел не допустим!");
        this.paid_count = paid_count;
    }

    public int getDebt_count() {
        return debt_count;
    }

    public void setDebt_count(int debt_count) throws AccountException {
        if (debt_count < 0) throw new AccountException("Ввод отрицательных чисел не допустим!");
        this.debt_count = debt_count;
    }
}
