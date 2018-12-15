package com.example.max00.republica_sazon.Activities.UI;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.max00.republica_sazon.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;

public class DialogScreen extends AppCompatDialogFragment {

    private EditText nombre, telefono;
    private CircleImageView profile_picture;

    private DialogScreenListener listener;
    private Uri imagen;

    private FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_editar, null);

        builder.setView(view)
                .setTitle("Editar Perfil")
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Guardar cambios", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nombre.getText().toString();
                        String numero = telefono.getText().toString();
                      //  Uri picture = profile_picture.getImageAlpha();
                        listener.onSaveChanges(imagen,name,numero);

                    }
                });

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        nombre = view.findViewById(R.id.et_nombre_editar);
        telefono = view.findViewById(R.id.et_phone_editar);
        profile_picture = view.findViewById(R.id.imgview_editar);
        profile_picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        nombre.setText(firebaseUser.getEmail());

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogScreenListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "Implemente DialogScreenListener");
        }

    }

    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri path = data.getData();
            imagen = data.getData();
            profile_picture.setImageURI(imagen);
        }
    }

    public interface DialogScreenListener{
       void onSaveChanges(Uri photo, String name, String phone);
       // void onSaveChanges(String name, String phone);
    }

}
