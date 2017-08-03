package com.gabrielsamojlo.offit;

import android.content.Context;

import retrofit2.Retrofit;

/**
 * Created by gabrielsamojlo on 20.07.2017.
 */

public class OffIt {

    public static class Builder {
        private Retrofit.Builder retrofit;
        private Context context;
        private boolean isEnabled;
        private NetworkSimulator networkSimulator;

        Builder(Context context) {
            this.context = context;
            this.isEnabled = false;
        }

        public Retrofit build(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return getModifiedRetrofit(isEnabled);
        }

        public Builder withRetrofitBuilder(Retrofit.Builder retrofit) {
            this.retrofit = retrofit;
            return this;
        }

        public Builder withNetworkSimulator(NetworkSimulator simulator) {
            this.networkSimulator = simulator;
            return this;
        }

        public Builder withNetworkSimulator() {
            this.networkSimulator = new DefaultNetworkSimulator();
            return this;
        }

        private Retrofit getModifiedRetrofit(boolean isEnabled) {
            retrofit.addCallAdapterFactory(MockableCallAdapterFactory.getInstance(context, networkSimulator, isEnabled));

            return retrofit.build();
        }

    }

    public static Builder withContext(Context context) {
        return new Builder(context);
    }

}
