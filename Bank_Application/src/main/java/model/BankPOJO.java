package model;

public class BankPOJO {
	
	final int status = 1;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String account_type;
	private Float balance;
	private Float new_balance;
	private int bank_id;
	private int sending_bank_id;
	private int recieving_bank_id;	
	private String description;
	private float deposit;
	private float withdraw;
			
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public int getBank_id() {
		return bank_id;
	}
	public void setBank_id(int bank_id) {
		this.bank_id = bank_id;
	}
	public int getSending_bank_id() {
		return sending_bank_id;
	}
	public void setSending_bank_id(int sending_bank_id) {
		this.sending_bank_id = sending_bank_id;
	}
	public int getRecieving_bank_id() {
		return recieving_bank_id;
	}
	public void setRecieving_bank_id(int recieving_bank_id) {
		this.recieving_bank_id = recieving_bank_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public float getDeposit() {
		return deposit;
	}
	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}
	public float getWithdraw() {
		return withdraw;
	}
	public void setWithdraw(float withdraw) {
		this.withdraw = withdraw;
	}
	public Float getNew_balance() {
		return new_balance;
	}
	public void setNew_balance(Float new_balance) {
		this.new_balance = new_balance;
	}
	
	


}
