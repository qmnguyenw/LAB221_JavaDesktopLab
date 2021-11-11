
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author vietddse62677
 */
public class CourseList {

    ArrayList<Courses> a = new ArrayList();

    public Courses getCourse(int index){
        return a.get(index);
    }
    public String addCourse(Courses c) {
        if (!checkCodeIsExist(c.getCode())) {
            a.add(c);
        } else {
            return "Code is Existed!";
        }
        return "";
    }

    public Courses searchCourseByCode(String code) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getCode().equalsIgnoreCase(code)) {
                return a.get(i);
            }
        }
        return null;
    }

    public String displayToList() {
        String s = "";
        for (int i = 0; i < a.size(); i++) {
            s += (a.get(i) + "\n");
        }
        return s;
    }

    public String validateInput(String code, String name, String credit) {

        if (code.isEmpty() || name.isEmpty() || credit.isEmpty()) {
            return "Code, Name and Credit cannot be empty!";
        }
        if (!checkCreditIsANumber(credit)) {
            return "Credit must be a Number";
        }
        if (!checkCreditIsValid(credit)) {
            return "Credit must be greater than " + Courses.MIN_CREDIT + " and less than or equal to " + Courses.MAX_CREDIT;
        }
        return "";
    }

    private boolean checkCodeIsExist(String code) {
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i).getCode().equalsIgnoreCase(code)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkCreditIsANumber(String credit) {
        try {
            int cr = Integer.parseInt(credit);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean checkCreditIsValid(String credit) {
        try {
            int cr = Integer.parseInt(credit);
            if (cr > Courses.MAX_CREDIT || cr <= Courses.MIN_CREDIT) {
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
}
