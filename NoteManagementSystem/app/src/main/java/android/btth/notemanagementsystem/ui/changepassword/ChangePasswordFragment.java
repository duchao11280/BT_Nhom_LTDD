package android.btth.notemanagementsystem.ui.changepassword;

import android.app.AlertDialog;
import android.btth.notemanagementsystem.AppDatabase;
import android.btth.notemanagementsystem.R;
import android.btth.notemanagementsystem.SigninActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public class ChangePasswordFragment extends Fragment {

    private EditText edtCurPass;
    private EditText edtNewPass;
    private EditText edtConfPass;
    private Button btnChangePass;
    private SharedPreferences sharedPreferences;
    private AppDatabase appDatabase;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_changepassword, container, false);
        //call Database
        appDatabase = AppDatabase.getInstance(getContext());
        initElement(root);
        sharedPreferences = this.getActivity().getSharedPreferences("dataLogin", Context.MODE_PRIVATE);
        btnChangePass.setOnClickListener(v -> {
            changePassword();
        });
        return root;
    }

    public void initElement(View root){
        edtCurPass = root.findViewById(R.id.edtCurPass);
        edtNewPass = root.findViewById(R.id.edtNewPass);
        edtConfPass = root.findViewById(R.id.edtConfPass);
        btnChangePass = root.findViewById(R.id.btnChangePassword);
    }

    public void changePassword(){
        String curpass = edtCurPass.getText().toString();
        String newpass = edtNewPass.getText().toString();
        String confpass = edtConfPass.getText().toString();
        String passSP = sharedPreferences.getString("userPass","");
        int userID = sharedPreferences.getInt("userID",0);
        if(!passSP.equals(curpass)){
            edtCurPass.setError("Incorect password");
            return;
        }
        else {
            edtCurPass.setError(null);
        }
        if(newpass.length()==0){
            edtNewPass.setError("Enter new password");
            return;
        }
        else {
            edtNewPass.setError(null);
        }
        if(curpass.equals(newpass)){
            edtNewPass.setError("Enter new password");
            return;
        }
        else {
            edtNewPass.setError(null);
        }
        if(!newpass.equals(confpass)){
            edtConfPass.setError("Password isn't match");
            return;
        }
        else{
            edtConfPass.setError(null);
        }
        appDatabase.userDao().changePassword(newpass,userID);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("userPass",newpass);
        editor.commit();
        edtConfPass.setText("");
        edtNewPass.setText("");
        edtCurPass.setText("");
    }

}
