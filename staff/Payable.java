package staff;

// This is a contract - any staff class that uses this
// MUST have these two methods or Java will give an error
public interface Payable {
    double calculateMonthlyPay(); // how much they earn per month
    double calculateBonus();      // how much extra bonus they get
}