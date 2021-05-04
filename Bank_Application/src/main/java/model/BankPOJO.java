package model;

public class BankPOJO {
	
	final int status = 1;
	private String email;
	private String deleteMemberId;
	private String password;
	private String firstName;
	private String lastName;
	private String accountType;
	private Float balance;
	private Float newBalance;
	private int bankId;
	private int deleteBankId;
	private int sendingBankId; 
	private int recievingBankId;	
	private String description;
	private String descriptionNotes;
	private float deposit;
	private float withdraw;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public Float getBalance() {
		return balance;
	}
	public void setBalance(Float balance) {
		this.balance = balance;
	}
	public Float getNewBalance() {
		return newBalance;
	}
	public void setNewBalance(Float newBalance) {
		this.newBalance = newBalance;
	}
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public int getDeleteBankId() {
		return deleteBankId;
	}
	public void setDeleteBankId(int deleteBankId) {
		this.deleteBankId = deleteBankId;
	}
	public int getSendingBankId() {
		return sendingBankId;
	}
	public void setSendingBankId(int sendingBankId) {
		this.sendingBankId = sendingBankId;
	}
	public int getRecievingBankId() {
		return recievingBankId;
	}
	public void setRecievingBankId(int recievingBankId) {
		this.recievingBankId = recievingBankId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public String getDeleteMemberId() {
		return deleteMemberId;
	}
	public void setDeleteMemberId(String deleteMemberId) {
		this.deleteMemberId = deleteMemberId;
	}
	public String getDescriptionNotes() {
		return descriptionNotes;
	}
	public void setDescriptionNotes(String descriptionNotes) {
		this.descriptionNotes = descriptionNotes;
	}
	
}
