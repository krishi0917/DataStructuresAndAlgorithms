package LeetcodePrograms.InterviewQuestions;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import java.io.*;
//import java.util.*;
//
///*
//# Given an org-chart denoted as a string in the following format:
//# "id:name:manager_id,id:name:manager_id"
//# Ex.
//# "1:Max:4,2:Ann:0,3:Albert:2,4:Edmond:2"
//# Print out an orgchart in simple ascii
//
//# For the example above print out the following:
//#
//# Ann
//#
//# - Edmond
//# -- Max
//  - Albert
// */
//
//// 2 - > ann
//// 3-> albert
//// 4-> edmund
//
//// (1,4) , (2,0) , (3,2) , (4,2)
//
class ChimeQuestion {

    class Employee{
        int id;
        int managerId;
        String empName;
        List<Employee> subordinates;
        public Employee(int id, int managerId , String empName){
            this.id = id;
            this.managerId = managerId;
            this.empName = empName;
        }
    }

    Map<Integer, Employee> map;
    Employee firstEmployee ;

    public void findOrg(String str){
        createEmployees(str);  //O(n)
        buildStructure (firstEmployee);
        printStructure(firstEmployee , 0  );
    }

    public void createEmployees(String str){
        map = new HashMap<>();
        String[] sArr = str.split(",");

        for(int i = 0 ; i < sArr.length ; i++){
            String[] record = sArr[i].split(":");

            int id = Integer.parseInt(record[0]);
            int managerId = Integer.parseInt(record[2]);

            Employee e = new Employee (id,managerId,record[1]);
            if(managerId == 0 ){
                firstEmployee = e; // starting point
            }
            map.put(id , e);
        }
    }

    public void buildStructure(Employee firstEmployee){
        Employee first = firstEmployee;
        List<Employee> employeelist = findEmployeeByManagerId(first);
        first.subordinates  = employeelist;
        if(employeelist.size()==0){
            return;
        }
        for(Employee e : employeelist){
            buildStructure(e);
        }
    }

    public List<Employee> findEmployeeByManagerId(Employee e){
        List<Employee> sameManagerEmpl = new ArrayList<>();
        for(Employee emp : map.values() ){
            if(emp.managerId == e.id){
                sameManagerEmpl.add(emp);
            }
        }

        return sameManagerEmpl;
    }

    public void printStructure(Employee e , int level){
        for(int i = 0 ; i < level ; i++){
            System.out.print("-");
        }

        System.out.print(e.empName);
        List<Employee> subordinates = e.subordinates;
        for(Employee emp : subordinates){
            printStructure(emp, level+1);
        }
    }

    public static void main(String[] args) {
        ChimeQuestion s = new ChimeQuestion();
        s.findOrg("1:Max:4,2:Ann:0,3:Albert:2,4:Edmond:2");
    }
}
