import java.util.ArrayList;

public class University {

    private ArrayList<Students> list = new ArrayList<>();

    public String listStudent(String name, int phone, int group) {

        Students st = new Students(name, phone, group);
        list.add(st);
        return "Студент добавлен";

    }

    public String delFromList(String name, int phone) {
        boolean result = false;
        for (Students st : list) {
            if (st.getName().equals(name) && st.getPhone() == phone) {
                list.remove(st);
                result = true;
                break;
            }
        }
        if (result) {
            return "Студент отчислен!";
        } else {
            return "Студент не обнаружен.";
        }

    }

    public String getList(int groupNumber) {
        String res = "Студенты группы: " + groupNumber + "\n";
        for (Students st : list) {
            if (st.getGroup() == groupNumber) {
                res += st.getName() + " " + st.getPhone() + "\n";

            }

        }
        return res;

    }
}
