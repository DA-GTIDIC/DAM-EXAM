package cat.udl.tidic.amd.dam_tips.repositories;///*
//package cat.udl.tidic.amd.dam_tips.repositories;
//
//import android.util.Log;
//
//import androidx.lifecycle.MutableLiveData;
//
//import java.util.List;
//
//import cat.udl.tidic.amd.dam_tips.dao.AccountDAO;
//import cat.udl.tidic.amd.dam_tips.models.Pregunta;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class PreguntesRepo {
//
//    private AccountDAO accountDAO;
//    private final String TAG = "TasksRepo";
//    //private UserRepository userRepo;
//
//    private MutableLiveData<List<Pregunta>> mResponseStudentTasksEnrolled;
//
//    public PreguntesRepo() {
//        tasksDAO = new PreguntesDAOImpl();
//        userRepo = new UserRepository();
//        this.mResponseStudentTasksEnrolled = new MutableLiveData<>();
//    }
//
//    public MutableLiveData<List<Pregunta>> getmResponseStudentTasksEnrolled() {
//        return mResponseStudentTasksEnrolled;
//    }
//
//    public void setmResponseStudentTasksEnrolled(MutableLiveData<List<Pregunta>> mResponseStudentTasksEnrolled) {
//        this.mResponseStudentTasksEnrolled = mResponseStudentTasksEnrolled;
//    }
//
//    public void getStudentTasks() {
//        System.out.println(userRepo.getToken());
//        tasksDAO.getStudentTasks(userRepo.getToken()).enqueue(new Callback<List<Pregunta>>() {
//            @Override
//            public void onResponse(Call<List<Pregunta>> call, Response<List<Pregunta>> response) {
//                Log.d(TAG, "Url: " + call.request().url());
//                Log.d(TAG, "req: " + call.request().toString());
//                if (response.code() == 200) {
//                    Log.d(TAG, "Se ha obtenido correctamente la lista de assignaturas.");
//                    mResponseStudentTasksEnrolled.setValue(response.body());
//                } else {
//                    Log.d(TAG, "No se ha obtenido correctamente la lista de assignaturas.");
//                    Log.d(TAG, "API code | message: " + response.code() + " | " + response.message());
//                    mResponseStudentTasksEnrolled.setValue(null);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Pregunta>> call, Throwable t) {
//                Log.d(TAG, "Error en la comunicación con API: " + t.getMessage());
//            }
//        });
//    }
//
//}
//*/
