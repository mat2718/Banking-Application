package model;

public class BankPOJO {
	
	final int status = 1;
	private String email;
	private String password;
	private String first_name;
	private String last_name;
	private String account_type;
	private String balance;
	private String bank_id;
	private String sending_bank_id;
	private String recieving_bank_id;	
	private String description;
	private String transaction_date;
			
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
	}
	public String getBank_id() {
		return bank_id;
	}
	public void setBank_id(String bank_id) {
		this.bank_id = bank_id;
	}
	public String getSending_bank_id() {
		return sending_bank_id;
	}
	public void setSending_bank_id(String sending_bank_id) {
		this.sending_bank_id = sending_bank_id;
	}
	public String getRecieving_bank_id() {
		return recieving_bank_id;
	}
	public void setRecieving_bank_id(String recieving_bank_id) {
		this.recieving_bank_id = recieving_bank_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	


}
