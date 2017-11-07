package com.xyzdrivers;

import java.util.Date;

/**
 *  This class will supply the back end functionality for the member dash board.
 *  It will provide the bridge between the UI and the database
 * @author Charlie Arnold
 */
public class MemberDB {
    
    private String id;
    private String name;
    private String address;
    private Date dob;
    private Date dor;
    private String STATUS;
    private double balance;

    public MemberDB(String id, String name, String address, Date dob, Date dor, String STATUS, double balance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dob = dob;
        this.dor = dor;
        this.STATUS = STATUS;
        this.balance = balance;
    }

    //This method will be called to get a string containing the memebrs balance
    public String checkBalance(){
        return String.valueOf(balance);
    }
    
    //This method assumes the Member has agreed to the payment.
    //  It checks that ammount is in currency form then checks member has enough
    //  in balance to make the payment. If so it reduces balance by ammount then
    //  returns true. Else it returns false.
    // TODO Access dataBase and update balance there
    public boolean makePayment(double ammount){
        //Check correct format
        if(isCurrencyFormat(ammount)){
            //Check member has enough money
            if(balance >= ammount){
                balance -= ammount;
                return true;
            }
        }
        return false;
    }
    
    //This method checks if a double is in currency format i.e 2 decimals after digit
    public boolean isCurrencyFormat(double d){
        String text = Double.toString(Math.abs(d));
        int decimalPlaces = text.length() - text.indexOf('.') - 1;
        if(decimalPlaces <= 2)
            return true;
        return false;
    }
    
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        if(isCurrencyFormat(balance))
            this.balance = balance;
    }
    public String getSTATUS() {
        return STATUS;
    }
    //TODO check input STATUS is suitable
    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }
    public Date getDor() {
        return dor;
    }
    public void setDor(Date dor) {
        this.dor = dor;
    }
    public Date getDob() {
        return dob;
    }
    public void setDob(Date dob) {
        this.dob = dob;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

}
