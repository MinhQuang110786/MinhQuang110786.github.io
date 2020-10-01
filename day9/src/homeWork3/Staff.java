package homeWork3;

public class Staff {
    private StaffMember[] staffList;

    public Staff(StaffMember[] staffList) {
        this.staffList = staffList;
    }

    public void payday(){
        for(StaffMember staff:staffList)
            System.out.println(staff);
    }
}
