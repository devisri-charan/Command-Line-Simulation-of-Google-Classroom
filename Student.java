import java.util.ArrayList;

interface Student {
    default void StudentsList(){
        System.out.println();
    }

    void ViewLectureMaterial();
    void ViewAssessments();
    void ViewPendingAssessments();
    void SubmitAssessments(String [] submission);
    void ViewGrades();
    void ViewComments();
    void AddComments(String comment,ArrayList<String> commentDetails);
}

class StudentClass implements Student{
    static ArrayList<String[]> pendingAssignments = InstructorClass.openAssignments;
    static ArrayList<String[]> submissions = new ArrayList<>();

    @Override
    public void ViewLectureMaterial() {
        InstructorClass.classMaterials.forEach((key, value) -> {
            if (value.size() == 3){
                System.out.println("Title of video: "+key);
                System.out.println("Video file: "+value.get(0));
                System.out.println("Date of upload: "+value.get(1));
                System.out.println("Uploaded by: "+value.get(2));
            }
            else {
                System.out.println("Title: "+key);
                System.out.println("Number of slides: "+value.get(0));
                for (int i=1;i<=value.size()-3;i++){
                    System.out.println("Slide"+i+": "+value.get(i));
                }
                System.out.println("Date of upload: "+value.get(value.size()-2));
                System.out.println("Uploaded by: "+value.get(value.size()-1));
            }
        });
    }

    @Override
    public void ViewAssessments() {
        int i =0;
        for (String[] assignment: InstructorClass.openAssignments) {
            System.out.println("ID:"+i+"Assignment:"+assignment[0]+"Max Marks:"+assignment[1]);
            System.out.println("-------------------------------------------------------------");
            i++;
        }
    }

    @Override
    public void ViewPendingAssessments(){
        int i =0;
        for (String[] assignment: pendingAssignments) {
            System.out.println("ID:"+i+"Assignment:"+assignment[0]+"Max Marks:"+assignment[1]);
            i++;
        }
    }

    @Override
    public void SubmitAssessments(String [] submission) {
        int assignmentID = Integer.parseInt(submission[0]);
        submissions.add(submission);
        pendingAssignments.remove(assignmentID);
        if (!InstructorClass.assignmentSubmissions.containsKey(assignmentID)){
            InstructorClass.assignmentSubmissions.put(assignmentID,submissions);
        }
        else{
            InstructorClass.assignmentSubmissions.remove(assignmentID);
            InstructorClass.assignmentSubmissions.put(assignmentID,submissions);
        }
    }

    @Override
    public void ViewGrades() {
        int i =0;
        for (String[] submission: submissions) {
            if (submission[4] == null){
                System.out.println("Ungraded submissions");
                System.out.println("Submission: "+submission[1]);
            }
            else{
                System.out.println("Graded submissions");
                System.out.println("Submission: "+submission[1]);
                System.out.println("Marks scored: "+submission[4]);
                System.out.println("Graded by:"+submission[5]);
            }
        }
    }

    @Override
    public void ViewComments() {
        InstructorClass.comments.forEach((key, value) -> {
            System.out.println(key+"  -"+value.get(0));
            System.out.println(value.get(1));
        });

    }

    @Override
    public void AddComments(String comment, ArrayList<String> commentDetails) {
        InstructorClass.comments.put(comment,commentDetails);
    }
}