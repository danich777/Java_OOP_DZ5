import java.util.ArrayList;

public class School {

    private ArrayList<Student> list = new ArrayList<>();

    public String listStudent(String name, int phone, int group) {

        Student st = new Student(name, phone, group);
        list.add(st);
        return "Учащийся добавлен";

    }

    public String delFromList(String name, int phone) {
        boolean result = false;
        for (Student st : list) {
            if (st.getName().equals(name) && st.getPhone() == phone) {
                list.remove(st);
                result = true;
                break;
            }
        }
        if (result) {
            return "Учащийся отчислен!";
        } else {
            return "Учащийся не обнаружен.";
        }

    }

    public String getList(int groupNumber) {
        String res = "Учащиеся класса: " + groupNumber + "\n";
        for (Student st : list) {
            if (st.getGroup() == groupNumber) {
                res = st.getName() + " " + st.getPhone() + "\n";

            }

        }
        return res;

    }

    public String editStudent(String name, int phone, int group) {
        for (Student st : list) {
            if (st.getName().equals(name)) {
                st.setPhone(phone);
                st.setGroup(group);
                break;
            }

        }
        return "Информация обновлена";
    }
}