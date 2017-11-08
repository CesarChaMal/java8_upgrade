package interfaces;

public class CompanyEmployee implements Company, Employee {
    private String first;
    private String last;

    public CompanyEmployee(String string, String string2) {
    	first = string;
    	last = string2;
	}

	@Override
    public String getName() {
        return String.format("%s working for %s",
                Employee.super.getName(), Company.super.getName());
    }

    @Override
    public void convertCaffeineToCode() {
        System.out.println("Coding...");
    }

    @Override
    public String getFirst() {
        return first;
    }

    @Override
    public String getLast() {
        return last;
    }
}
