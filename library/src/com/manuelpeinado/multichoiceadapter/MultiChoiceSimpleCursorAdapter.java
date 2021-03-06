/*
 * Copyright (C) 2013 Manuel Peinado
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.manuelpeinado.multichoiceadapter;

import java.util.Set;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;

import com.actionbarsherlock.view.ActionMode;

/**
 */
public abstract class MultiChoiceSimpleCursorAdapter extends SimpleCursorAdapter 
                                                     implements ActionMode.Callback {
    
    private MultiChoiceAdapterHelper helper = new MultiChoiceAdapterHelper(this) {
        @Override
        protected long positionToSelectionHandle(int position) {
            return getItemId(position);
        }
    };
    
    public MultiChoiceSimpleCursorAdapter(Bundle savedInstanceState, Context context, int layout, Cursor cursor, String[] from, int[] to, int flags) {
        super(context, layout, cursor, from, to, flags);
        helper.restoreSelectionFromSavedInstanceState(savedInstanceState);
    }
    
    public void setAdapterView(AdapterView<? super BaseAdapter> adapterView) {
        helper.setAdapterView(adapterView);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        helper.setOnItemClickListener(listener);
    }
    
    public void save(Bundle outState) {
        helper.save(outState);
    }

    public void select(long itemId, boolean selected) {
        helper.select(itemId, selected);
    }

    public void select(long itemId) {
        helper.select(itemId);
    }

    public void unselect(long itemId) {
        helper.unselect(itemId);
    }

    public Set<Long> getSelection() {
        return helper.getSelection();
    }

    public int getSelectionCount() {
        return helper.getSelectionCount();
    }

    public boolean isSelected(long itemId) {
        return helper.isSelected(itemId);
    }

    protected void finishActionMode() {
       helper.finishActionMode();
    }

    protected Context getContext() {
        return helper.getContext();
    }
    
    @Override
    public void onDestroyActionMode(ActionMode mode) {
        helper.onDestroyActionMode(mode);
    }

    @Override
    public final View getView(int position, View convertView, ViewGroup parent) {
        View viewWithoutSelection = super.getView(position, convertView, parent);
        return helper.getView(position, viewWithoutSelection);
    }
}