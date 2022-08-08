import java.util.ArrayList;
import java.util.Scanner;

public class Backpack {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        System.out.println("""
                    Welcome to Backpack
                    1. Enter as an instructor
                    2. Enter as a student
                    3. Exit""");
        while (true){
            int query = input.nextInt();
            if (query==1){
                InstructorClass instructor = new InstructorClass();
                System.out.println("Instructors");
                System.out.println("""
                        0 - I0
                        1 - I1""");
                System.out.print("Choose ID: ");
                int ID = input.nextInt();
                while (true){
                    System.out.println("\nWelcome I"+ID);
                    System.out.println("""
                            INSTRUCTOR MENU
                            1. Add class material
                            2. Add assessments
                            3. View lecture materials
                            4. View assessments
                            5. Grade assessments
                            6. Close assessment
                            7. View comments
                            8. Add comments
                            9. Logout""");
                    int i = input.nextInt();

                    if (i==1){
                        System.out.println("""
                                1. Add Lecture Slide
                                2. Add Lecture Video""");
                        int l = input.nextInt();
                        if (l==1){
                            ArrayList<String> Content = new ArrayList<>();
                            System.out.print("Enter topic of slides: ");
                            String topic = input.nextLine();

                            System.out.print("Enter number of slides: ");
                            String slides = input.nextLine();
                            int n = Integer.parseInt(slides);
                            //int n = input.nextInt();
                            Content.add(String.valueOf(n));

                            System.out.println("Enter content of slides");
                            for (int j=1;j<=n;j++){
                                System.out.print("Content of slide "+j+": ");
                                String content = input.nextLine();
                                Content.add(content);
                            }

                            long millis=System.currentTimeMillis();
                            java.util.Date date=new java.util.Date(millis);
                            Content.add(String.valueOf(date));

                            String uploadedBy = "I"+ ID;
                            Content.add(uploadedBy);

                            instructor.AddClassMaterial(topic,Content);
                        }

                        if (l==2){
                            ArrayList<String> Content = new ArrayList<>();
                            System.out.print("Enter topic of video: ");
                            String topic = input.nextLine();
                            System.out.print("Enter filename of video:");
                            String filename = input.nextLine();
                            if (!filename.contains(".rar")){
                                Content.add(filename);

                                long millis=System.currentTimeMillis();
                                java.util.Date date=new java.util.Date(millis);
                                Content.add(String.valueOf(date));

                                String uploadedBy = "I"+ ID;
                                Content.add(uploadedBy);

                                instructor.AddClassMaterial(topic,Content);
                            }
                            else{
                                System.out.println(".rar is not accepted");
                            }
                        }
                    }

                    if (i==2){
                        System.out.println("""
                                1. Add Assignment
                                2. Add Quiz""");
                        int a = input.nextInt();

                        if (a==1){
                            System.out.print("Enter problem statement: ");
                            String problemStatement = input.nextLine();

                            System.out.print("Enter max marks: ");
                            String maxiMarks = input.nextLine();
                            int maxMarks = Integer.parseInt(maxiMarks);
                            //int maxMarks = input.nextInt();

                            String [] assessment = {problemStatement, String.valueOf(maxMarks)};
                            instructor.AddAssessments(assessment);
                        }

                        if (a==2){
                            System.out.print("Enter quiz question: ");
                            String quizQuestion = input.next();

                            String [] assessment = {quizQuestion, String.valueOf(1)};
                            instructor.AddAssessments(assessment);
                        }
                    }

                    if (i==3){
                        //Title: Slide 1
                        //Number of slides: 2
                        //Slide 1: Content 1
                        //Slide 2: Content 2
                        //Date of upload: Thu Oct 14 23:25:25 IST 2021
                        // Uploaded by: I0

                        //Title of video: Lecture 1
                        //Video file: lecture1.mp4
                        //Date of upload: Thu Oct 14 23:25:39 IST 2021
                        //Uploaded by: I0
                        instructor.ViewLectureMaterial();
                    }

                    if (i==4){
                        System.out.println("List of assessments");
                        //ID: 0 Assignment: Assignment 1 problem Max Marks: 5
                        // ----------------
                        //ID: 1 Question: Name a language that supports OOPS?
                        //----------------
                        instructor.ViewAssessments();
                    }

                    if (i==5){
                        System.out.println("List of assessments");
                        //ID: 0 Assignment: Assignment 1 problem Max Marks: 5
                        // ----------------
                        //ID: 1 Question: Name a language that supports OOPS?
                        //----------------
                        instructor.ViewAssessments();

                        System.out.println("Enter ID of assessment to view submissions: ");
                        int assessmentID = input.nextInt();

                        System.out.println("Choose ID from these ungraded submissions");
                        instructor.ViewUngradedSubmissions(assessmentID);
                        //0. S0
                        //1. S1
                        int studentID = input.nextInt();
                        instructor.submissionDetails(assessmentID,studentID);
                        //Submission:
                        //Submission: assign1_s0.zip
                        //-------------------------------
                        //Max Marks: 5

                        System.out.print("Marks scored: ");
                        int marksScored = input.nextInt();

                        String gradedBy = "I"+ ID;
                        instructor.GradeAssessments(assessmentID,studentID, new String[]{String.valueOf(marksScored), gradedBy});
                    }

                    if (i==6){
                        System.out.println("List of Open Assignments:");
                        instructor.ViewOpenAssignments();
                        //ID: 0 Assignment: Assignment 1 problem Max Marks: 5
                        // ----------------
                        //ID: 1 Question: Name a language which supports OOPS?
                        // ----------------
                        System.out.print("Enter id of assignment to close: ");
                        int assignmentID = input.nextInt();

                        instructor.CloseAssessments(assignmentID);
                        System.out.println("Assignment"+assignmentID+" closed");
                    }

                    if (i==7){
                        instructor.ViewComments();
                    }

                    if (i==8){
                        ArrayList<String> commentDetails = new ArrayList<>();
                        System.out.print("Enter comment: ");
                        String comment = input.next();

                        String uploadedBy = "I"+ ID;
                        commentDetails.add(uploadedBy);

                        long millis=System.currentTimeMillis();
                        java.util.Date date=new java.util.Date(millis);
                        commentDetails.add(String.valueOf(date));

                        instructor.AddComments(comment,commentDetails);
                    }

                    if (i==9){
                        System.out.println("Logged Out successfully");
                        System.out.println("""
                            Welcome to Backpack
                            1. Enter as an instructor
                            2. Enter as a student
                            3. Exit""");
                        break;
                    }
                }
            }

            if (query==2){
                StudentClass student = new StudentClass();
                System.out.println("Students");
                System.out.println("""
                        0 - S0
                        1 - S1
                        2 - S2""");
                System.out.print("Choose ID: ");
                int ID = input.nextInt();
                while (true) {
                    System.out.println("Welcome S" + ID);
                    System.out.println("""
                            STUDENT MENU
                            1. View lecture materials
                            2. View assessments
                            3. Submit assessment
                            4. View grades
                            5. View comments
                            6. Add comments
                            7. Logout""");

                    int s = input.nextInt();
                    if (s == 1) {
                        //Title: Slide 1
                        //Slide 1: Content 1
                        //Slide 2: Content 2
                        //Number of slides: 2
                        //Date of upload: Thu Oct 14 23:25:25 IST 2021
                        // Uploaded by: I0

                        //Title of video: Lecture 1
                        //Video file: lecture1.mp4
                        //Date of upload: Thu Oct 14 23:25:39 IST 2021
                        // Uploaded by: I0
                        student.ViewLectureMaterial();
                    }

                    if (s == 2) {
                        //ID: 0 Assignment: Assignment 1 problem Max Marks: 5
                        //----------------
                        //ID: 1 Question: Name a language that supports OOPS?
                        //----------------
                        student.ViewAssessments();
                    }

                    if (s == 3) {
                        if (StudentClass.pendingAssignments.size() !=0) {
                            System.out.println("Pending Assessments");
                            //ID: 0 Assignment: Assignment 1 problem Max Marks: 5
                            //ID: 1 Question: Name a language that supports OOPS?
                            System.out.println("Enter ID of assessment: ");
                            int assessmentID = input.nextInt();
                            System.out.println("Enter filename of assignment: ");
                            String fileName = input.next();

                            String submittedBy = "I" + ID;

                            String[] submission = {String.valueOf(assessmentID), fileName, submittedBy, InstructorClass.Assessments.get(assessmentID)[1], null,null};
                        }
                        else
                            System.out.println("No pending assessments");

                    }

                    if (s == 4) {
                        //Graded submissions
                        //
                        //Ungraded submissions
                        //Submission: assign1_s0.zip
                        student.ViewGrades();
                    }

                    if (s == 5) {
                        //Welcome to the course - I0
                        //Thu Oct 14 23:26:26 IST 2021
                        //
                        //Hello I am a student - S0
                        //Thu Oct 14 23:32:47 IST 2021

                        student.ViewComments();
                    }

                    if (s == 6) {
                        ArrayList<String> commentDetails = new ArrayList<>();
                        System.out.print("Enter comment: ");
                        String comment = input.next();
                        String uploadedBy = "I"+ ID;
                        commentDetails.add(uploadedBy);

                        long millis=System.currentTimeMillis();
                        java.util.Date date=new java.util.Date(millis);
                        commentDetails.add(String.valueOf(date));

                        student.AddComments(comment,commentDetails);
                    }

                    if (s == 7) {
                        System.out.println("Logged Out successfully");
                        System.out.println("""
                            Welcome to Backpack
                            1. Enter as an instructor
                            2. Enter as a student
                            3. Exit""");
                        break;
                    }
                }
            }

            if (query==3){
                System.out.println("Thank you for visiting backpack :)");
                break;
            }
        }
    }
}
