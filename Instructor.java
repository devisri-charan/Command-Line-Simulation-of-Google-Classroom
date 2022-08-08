import java.util.ArrayList;
import java.util.Hashtable;

interface Instructor {
    default void InstructorList() {
        System.out.println();
    }

    void AddClassMaterial(String topic,ArrayList<String> content);
    void AddAssessments(String [] assessment);
    void ViewLectureMaterial();
    void ViewAssessments();
    void ViewOpenAssignments();
    void ViewUngradedSubmissions(int assignmentID);
    void submissionDetails(int assignmentID,int studentID);
    void GradeAssessments(int assignmentID,int studentID,String [] grade);
    void CloseAssessments(int assignmentID);
    void ViewComments();
    void AddComments(String comment,ArrayList<String> commentDetails);
}

class InstructorClass implements Instructor{
    static Hashtable<String, ArrayList<String>> classMaterials = new Hashtable<>();
    static ArrayList<String[]> Assessments = new ArrayList<>();
    static ArrayList<String[]> openAssignments = new ArrayList<>();
    static Hashtable<String, ArrayList<String>> comments = new Hashtable<>();
    static Hashtable<Integer, ArrayList<String[]>> assignmentSubmissions = new Hashtable<>();

    @Override
    public void AddClassMaterial(String topic, ArrayList<String> content) {
        classMaterials.put(topic,content);
    }

    @Override
    public void AddAssessments(String [] assessment) {
        Assessments.add(assessment);
        openAssignments.add(assessment);
    }

    @Override
    public void ViewLectureMaterial() {
        classMaterials.forEach((key, value) -> {
            if (value.size() == 3){
                System.out.println("Title of video: "+key);
                System.out.println("Video file: "+value.get(0));
                System.out.println("Date of upload: "+value.get(1));
                System.out.println("Uploaded by: "+value.get(2)+"\n");
            }
            else {
                System.out.println("Title: "+key);
                System.out.println("Number of slides: "+value.get(0));
                for (int i=1;i<=value.size()-3;i++){
                    System.out.println("Slide"+i+": "+value.get(i));
                }
                System.out.println("Date of upload: "+value.get(value.size()-2));
                System.out.println("Uploaded by: "+value.get(value.size()-1)+"\n");
            }
        });
    }

    @Override
    public void ViewAssessments() {
        int i =0;
        for (String[] assessment: Assessments) {
            System.out.println("ID:"+i+"Assignment:"+assessment[0]+"Max Marks:"+assessment[1]);
            System.out.println("-------------------------------------------------------------");
            i++;
        }
    }

    @Override
    public void ViewOpenAssignments(){
        int i =0;
        for (String[] assignment: openAssignments) {
            System.out.println("ID:"+i+"Assignment:"+assignment[0]+"Max Marks:"+assignment[1]);
            System.out.println("-------------------------------------------------------------");
            i++;
        }
    }

    @Override
    public void ViewUngradedSubmissions(int assignmentID){
        ArrayList<String[]> ungradedSubmissions = assignmentSubmissions.get(assignmentID);
        int i =0;
        for (String[] submission: ungradedSubmissions) {
            System.out.println(i+"."+submission[2]);
            i++;
        }
    }

    @Override
    public void submissionDetails(int assignmentID,int studentID) {
        String studentName = "S"+studentID;
        ArrayList<String[]> ungradedSubmissions = assignmentSubmissions.get(assignmentID);
        for (String[] submission: ungradedSubmissions) {
            if (studentName.equals(submission[2])){
                System.out.println("Submission: "+submission[2]);
                System.out.println("-----------------------------------------");
                System.out.println("Max Marks: "+submission[3]);
            }
        }
    }

    @Override
    public void GradeAssessments(int assignmentID,int studentID,String [] grade) {
        String studentName = "S"+studentID;
        ArrayList<String[]> ungradedSubmissions = assignmentSubmissions.get(assignmentID);
        for (String[] submission: ungradedSubmissions) {
            if (studentName.equals(submission[2])){
                submission[4] = grade[0];
                submission[5] = grade[1];
                break;
            }
        }
    }

    @Override
    public void CloseAssessments(int assignmentID) {
        openAssignments.remove(assignmentID);
    }

    @Override
    public void ViewComments() {
        comments.forEach((key, value) -> {
            System.out.println(key+"  -"+value.get(0));
            System.out.println(value.get(1));
        });
    }

    @Override
    public void AddComments(String comment,ArrayList<String> commentDetails) {
        comments.put(comment,commentDetails);
    }
}
