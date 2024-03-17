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
    public static void main(String[] args) throws IOException {

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
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, retrofit2.Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    System.out.println("Response from GitHub API:");
                    List<Repo> repoList = response.body();
                    for (Repo repo : repoList) {
                        System.out.println(repo);
                    }
                } else {
                    System.out.println("Something went wrong with the Retrofit call!");
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                System.out.println("Network Error: " + t.getMessage());
            }
        });
    }
}

