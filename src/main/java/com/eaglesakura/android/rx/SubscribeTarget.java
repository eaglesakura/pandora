package com.eaglesakura.android.rx;

/**
 * 実行スレッド選定
 */
@Deprecated
public enum SubscribeTarget {
    /**
     * 直列化されたパイプラインで制御する
     */
    Pipeline {
        @Override
        int getKeepAliveMs() {
            return 1000 * 5;
        }

        @Override
        int getThreadPoolNum() {
            return 1;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.LocalQueue;
        }
    },

    /**
     * 並列化されたスレッドプールで制御する
     */
    Parallels {
        @Override
        int getKeepAliveMs() {
            return 1000 * 5;
        }

        @Override
        int getThreadPoolNum() {
            return 3;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.LocalParallel;
        }
    },

    /**
     * プロセス内で共有される直列化された処理
     */
    GlobalPipeline {
        @Override
        int getKeepAliveMs() {
            return 1000 * 3;
        }

        @Override
        int getThreadPoolNum() {
            return 1;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.GlobalQueue;
        }
    },

    /**
     * プロセス内で共有される並列化された処理
     */
    GlobalParallels {
        @Override
        int getKeepAliveMs() {
            return 1000 * 3;
        }

        @Override
        int getThreadPoolNum() {
            return 5;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.GlobalParallel;
        }
    },

    /**
     * ネットワーク処理用スレッド
     *
     * これはグローバルで共有され、適度なスレッド数に保たれる
     */
    Network {
        @Override
        int getKeepAliveMs() {
            return 500;
        }

        @Override
        int getThreadPoolNum() {
            return 3;
        }


        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.Network;
        }
    },

    /**
     * 専用スレッドを生成する
     */
    NewThread {
        @Override
        int getKeepAliveMs() {
            return 0;
        }

        @Override
        int getThreadPoolNum() {
            return 0;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.NewThread;
        }
    },

    /**
     * UiThreadで処理する
     */
    MainThread {
        @Override
        int getKeepAliveMs() {
            return 0;
        }

        @Override
        int getThreadPoolNum() {
            return 0;
        }

        @Override
        ExecuteTarget asExecuteTarget() {
            return ExecuteTarget.MainThread;
        }
    };

    /**
     * スレッドキャッシュ時間を取得する
     */
    abstract int getKeepAliveMs();

    /**
     * 最大スレッド数を取得する
     */
    abstract int getThreadPoolNum();

    @Deprecated
    abstract ExecuteTarget asExecuteTarget();
}
