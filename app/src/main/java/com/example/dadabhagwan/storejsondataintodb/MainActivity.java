    package com.example.dadabhagwan.storejsondataintodb;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    ProgressDialog pDialog;
    public  ArrayList<Actors> actorData;
    List<Actors> actorsList;
    public DataBaseHandler db;
    private ListView lv;
    Actors setData;
    public static String url = "http://microblogging.wingnity.com/JSONParsingTutorial/jsonActors";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = (ListView) findViewById(R.id.list);
        actorData=new ArrayList<Actors>();
        db=new DataBaseHandler(this);
        new GetActors(this).execute();

    }
    private class GetActors extends AsyncTask<Void,Void,Void>
    {
        private Context c;
        GetActors(Context c)
        {
            this.c=c;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... voids) {

          HttpHandler httphandler=new HttpHandler();
          String Jsondata=httphandler.makeServiceCall(url);

          if(Jsondata!=null)
          {
              try {
                  JSONObject jsonObject=new JSONObject(Jsondata);

                  JSONArray jsonArray=jsonObject.getJSONArray("actors");

                  for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject c=jsonArray.getJSONObject(i);
                    Actors setData=new Actors();
                    String name=c.getString("name");
                    setData.setName(name);

                     String description=c.getString("description");
                     setData.setDescription(description);

                      String dob=c.getString("dob");
                      setData.setDob(dob);

                      String country=c.getString("country");
                      setData.setCountry(country);

                      String height=c.getString("height");
                      setData.setHeight(height);

                      String spouse=c.getString("spouse");
                      setData.setSpouse(spouse);

                      String children=c.getString("children");
                      setData.setChildren(children);

                      String image=c.getString("image");
                      setData.setImage(image);
                      db.addActor(setData);
                      actorData.add(setData);




                  }

              } catch (JSONException e) {
                  runOnUiThread(new Runnable() {
                      @Override
                      public void run() {
                          Toast.makeText(getApplicationContext(),
                                  "Json parsing error: " ,
                                  Toast.LENGTH_LONG)
                                  .show();
                      }
                  });


              }
          }
            else
          {
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      Toast.makeText(getApplicationContext(),
                              "Couldn't get json from server. Check LogCat for possible errors!",
                              Toast.LENGTH_LONG)
                              .show();
                  }
              });
          }




            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (pDialog.isShowing())
                pDialog.dismiss();

           actorsList= db.getAllContacts();
            ListAdapter adapter = new ListAdapter(actorsList,c);
            lv.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
        }
    }

