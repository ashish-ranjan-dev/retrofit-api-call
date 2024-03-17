package org.example;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args)  {

        System.out.println("Hello");
        OkHttpClient client = new OkHttpClient();

        String url = "https://example.com/";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) {
                System.out.println("Something went wring !!!!");
            }
//            System.out.println(response.body().string());
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        GitHubService service = retrofit.create(GitHubService.class);
        Call<List<Repo>> repos = service.listRepos("octocat");
        try {
            List<Repo> repoList = repos.execute().body();
            for (Repo repo : repoList) {
                System.out.println(repo);
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }
}

