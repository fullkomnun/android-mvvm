/*
 * Copyright 2016 Andrei Zaiats.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.azaiats.androidmvvm.core.delegates;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import io.github.azaiats.androidmvvm.core.common.NavigatingViewModel;
import io.github.azaiats.androidmvvm.core.common.Navigator;

/**
 * A navigated delegate for Activities lifecycle.
 *
 * @param <T> the type of {@link Navigator}
 * @param <S> the type of {@link ViewDataBinding}
 * @param <U> the type of binded {@link NavigatingViewModel}
 * @author Andrei Zaiats
 * @since 0.1.1
 */
public class NavigatingActivityDelegate<T extends Navigator, S extends ViewDataBinding,
        U extends NavigatingViewModel<T>> extends ActivityDelegate<S, U> {

    private final NavigatingActivityDelegateCallback<T, S, U> callback;

    /**
     * Create delegate for activity.
     *
     * @param callback          the {@link ActivityDelegateCallback} for this delegate
     * @param delegatedActivity the {@link Activity} for delegation
     */
    public NavigatingActivityDelegate(@NonNull NavigatingActivityDelegateCallback<T, S, U> callback,
                                      @NonNull Activity delegatedActivity) {
        super(callback, delegatedActivity);
        this.callback = callback;
    }

    @Override
    @CallSuper
    public void onCreate() {
        super.onCreate();
        if (viewModel != null) {
            viewModel.setNavigator(callback.getNavigator());
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.setNavigator(null);
        }
    }
}
