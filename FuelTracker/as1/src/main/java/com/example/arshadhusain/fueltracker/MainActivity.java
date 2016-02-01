package com.example.arshadhusain.fueltracker;

        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.gson.Gson;
        import com.google.gson.reflect.TypeToken;

        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import java.io.FileOutputStream;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;
        import java.lang.reflect.Type;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.concurrent.TimeUnit;

public class MainActivity extends Activity implements AdapterView.OnItemClickListener {

    private Button button;
    private EditText editTextMainScreen;
    private ListView oldTweetsList;
    private static final String FILENAME = "FuelTracker.sav";
    private ArrayList<FuelLog> FuelLogs = new ArrayList<FuelLog>();
    ArrayAdapter<FuelLog> adapter;

    final Context context = this;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        // components from main.xml
        button = (Button) findViewById(R.id.button);
        //editTextMainScreen = (EditText) findViewById(R.id.editTextResult);
        oldTweetsList = (ListView) findViewById(R.id.oldTweetsList);


        loadFromFile();
        adapter = new ArrayAdapter<FuelLog>(this,
                R.layout.list_item, FuelLogs);
        oldTweetsList.setAdapter(adapter);

        oldTweetsList.setOnItemClickListener(this);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View view) {

                // get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);

                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);



                final EditText input = (EditText) promptView.findViewById(R.id.userInput);
                final EditText input1 = (EditText) promptView.findViewById(R.id.userInput1);
                final EditText input2 = (EditText) promptView.findViewById(R.id.userInput2);
                final EditText input3 = (EditText) promptView.findViewById(R.id.userInput3);
                final EditText input4 = (EditText) promptView.findViewById(R.id.userInput4);
                //final EditText input5 = (EditText) promptView.findViewById(R.id.userInput5);
                final EditText input6 = (EditText) promptView.findViewById(R.id.userInput6);




                // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                //editTextMainScreen.setText(input.getText());
                                setResult(RESULT_OK);
                                String station = input.getText().toString();
                                String odometer = input1.getText().toString();
                                String fuelGrade = input2.getText().toString();
                                String fuelAmount = input3.getText().toString();
                                String fuelUnitCost = input4.getText().toString();
                                //String fuelCost = input5.getText().toString();
                                String date = input6.getText().toString(); //Date

                                FuelLog log = new FuelLog(date, station, odometer, fuelGrade, fuelAmount, fuelUnitCost);

                                FuelLogs.add(log);
                                adapter.notifyDataSetChanged();

                                saveInFile();
                                //finish();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();

            }
        });
    }

    private String[] loadFromFile() {
        ArrayList<String> fuelList = new ArrayList<String>();
        try {
            FileInputStream inputFile = openFileInput(FILENAME);
            BufferedReader input = new BufferedReader(new InputStreamReader(inputFile));
            //String line = input.readLine();
            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<FuelLog>>() {}.getType();
            FuelLogs = gson.fromJson(input, listType);
            /*while (line != null) {
                fuelList.add(line);
                line = input.readLine();
            }*/

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            FuelLogs = new ArrayList<FuelLog>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //e.printStackTrace();
            throw new RuntimeException();
        }
        return fuelList.toArray(new String[fuelList.size()]);
    }

    private void saveInFile() {
        try {
            //FileOutputStream fos = openFileOutput(FILENAME,
                    //Context.MODE_APPEND);\
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(FuelLogs, out);
            //fos.write(new String(text6 + " | " + text + " | " + text1 + " | " + text2 + " | " + text3 + " | " + text4 + " | " + text5 + " \n ")
                   // .getBytes());
            out.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public void onItemClick(AdapterView<?> arg0, View view, final int position, long id) {

        //Toast.makeText(getApplicationContext(), "Etudiant a la position: "+ position, Toast.LENGTH_LONG).show();
        //FuelLogs.set()
        //button.setOnClickListener(new OnClickListener() {

           // @Override
           // public void onClick(View view) {

                // get prompts.xml view
                LayoutInflater layoutInflater = LayoutInflater.from(context);

                View promptView = layoutInflater.inflate(R.layout.prompts, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

                // set prompts.xml to be the layout file of the alertdialog builder
                alertDialogBuilder.setView(promptView);



                final EditText input = (EditText) promptView.findViewById(R.id.userInput);
                final EditText input1 = (EditText) promptView.findViewById(R.id.userInput1);
                final EditText input2 = (EditText) promptView.findViewById(R.id.userInput2);
                final EditText input3 = (EditText) promptView.findViewById(R.id.userInput3);
                final EditText input4 = (EditText) promptView.findViewById(R.id.userInput4);
                //final EditText input5 = (EditText) promptView.findViewById(R.id.userInput5);
                final EditText input6 = (EditText) promptView.findViewById(R.id.userInput6);

                input.setText(FuelLogs.get(position).getStation(), TextView.BufferType.EDITABLE);
                input1.setText(FuelLogs.get(position).getOdometer(), TextView.BufferType.EDITABLE);
                input2.setText(FuelLogs.get(position).getFuelGrade(), TextView.BufferType.EDITABLE);
                input3.setText(FuelLogs.get(position).getFuelAmount(), TextView.BufferType.EDITABLE);
                input4.setText(FuelLogs.get(position).getFuelUnitCost(), TextView.BufferType.EDITABLE);
                //input5.setText(FuelLogs.get(position).getFuelCost(), TextView.BufferType.EDITABLE);
                input6.setText(FuelLogs.get(position).getDate(), TextView.BufferType.EDITABLE);




        // setup a dialog window
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // get user input and set it to result
                                //editTextMainScreen.setText(input.getText());
                                setResult(RESULT_OK);




                                String station = input.getText().toString();
                                String odometer = input1.getText().toString();
                                String fuelGrade = input2.getText().toString();
                                String fuelAmount = input3.getText().toString();
                                String fuelUnitCost = input4.getText().toString();
                                //String fuelCost = input5.getText().toString();
                                String date = input6.getText().toString(); //Date



                                FuelLogs.get(position).setStation(station);    //LEFT OFF HERE. MAKE VARIABLE "POSITION" WORK IN THIS CONTEXT
                                FuelLogs.get(position).setOdometer(odometer);
                                FuelLogs.get(position).setFuelGrade(fuelGrade);
                                FuelLogs.get(position).setFuelAmount(fuelAmount);
                                FuelLogs.get(position).setFuelUnitCost(fuelUnitCost);
                                //FuelLogs.get(position).setFuelCost(fuelCost);
                                FuelLogs.get(position).setDate(date);
                                //FuelLogs.get(position).date = date;



                                if(FuelLogs.get(position).getOdometer() == "false"|| FuelLogs.get(position).getFuelAmount() == "false" || FuelLogs.get(position).getFuelUnitCost() == "false" || FuelLogs.get(position).getFuelCost() == "false" )
                                {
                                    Toast.makeText(getApplicationContext(), "Please input numeric values to entries marked 'false'", Toast.LENGTH_LONG).show();

                                }
                                /*FuelLog log = new FuelLog(date, station, odometer, fuelGrade, fuelAmount, fuelUnitCost, fuelCost);

                                FuelLogs.add(log);*/
                                adapter.notifyDataSetChanged();

                                saveInFile();
                               // finish();
                            }
                        })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                    }
                                });

                // create an alert dialog
                AlertDialog alertD = alertDialogBuilder.create();

                alertD.show();

            //}
       // });

    }
}