package ru.job4j.sort;

import java.util.*;

/**
 * Класс-сортировщик подразделений.
 * @author Elena Kartashova (kartashova.ee@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class DepartmentSort {

    /**
     * Преобразует массив строк с кодами подразделений в отсортированную по возрасатнию коллекцию.
     * @param departments исходный массив строк с кодами подразделений.
     * @return отсортированный список подразделений ArrayList.
     */
    public List<String> sortDepartmentAsc(String[] departments) {
        List<Department> departmentList = this.completeDepartmentArray(departments);
        List<String> result = this.arrayToString(departmentList);
        result.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        return result;
    }

    /**
     * Преобразует массив строк с кодами подразделений в отсортированную по убыванию коллекцию.
     * @param departments исходный массив строк с кодами подразделений.
     * @return отсортированный список подразделений ArrayList.
     */
    public List<String> sortDepartmentDesc(String[] departments) {
        List<Department> departmentList = this.completeDepartmentArray(departments);
        departmentList.sort(new Comparator<Department>() {
            @Override
            public int compare(Department o1, Department o2) {
                String[] deps1 = o1.getDepCode();
                String[] deps2 = o2.getDepCode();
                int result = 0;
                for (int i = 0; i < deps1.length; i++) {
                    if (deps2.length > i) {
                        result = deps2[i].compareTo(deps1[i]);
                        if (result != 0) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
                if (result == 0 && deps1.length != deps2.length) {
                    result = (deps1.length > deps2.length) ? 1 : -1;
                }
                return result;
            }
        });
        return this.arrayToString(departmentList);
    }

    private List<String> arrayToString(List<Department> departmentArray) {
        List<String> result = new ArrayList<>();
        for (Department department : departmentArray) {
            StringBuilder resultString = new StringBuilder();
            resultString.append(department.getDepCode()[0]);
            for (int i = 1; i < department.getDepCode().length; i++) {
                resultString.append("\\").append(department.getDepCode()[i]);
            }
            result.add(resultString.toString());
        }
        return result;
    }

    private List<Department> completeDepartmentArray(String[] departments) {
        List<Department> departmentsArray = new ArrayList<>();
        List<Department> departmentRetailList = new ArrayList<>();
        for (String department : departments) {
            departmentsArray.add(new Department(department.split("\\\\")));
        }
        for (Department department : departmentsArray) {
            String[] dep = department.getDepCode();
            String[] temp;
            for (int i = 0; i < dep.length; i++) {
                temp = Arrays.copyOf(dep, i + 1);
                Department searchDep = new Department(temp);
                if (!(departmentsArray.contains(searchDep) || departmentRetailList.contains(searchDep))) {
                    departmentRetailList.add(searchDep);
                }
            }
        }
        departmentsArray.addAll(departmentRetailList);
        return departmentsArray;
    }
}