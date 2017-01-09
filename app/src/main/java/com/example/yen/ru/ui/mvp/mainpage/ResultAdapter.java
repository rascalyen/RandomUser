package com.example.yen.ru.ui.mvp.mainpage;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.yen.ru.R;
import com.example.yen.ru.data.model.Result;
import com.example.yen.ru.ui.navigation.Navigator;
import com.squareup.picasso.Picasso;
import java.util.List;
import javax.inject.Inject;
import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_ITEM = 0;
    private static final int VIEW_TYPE_FOOTER = 1;
    private Picasso picasso;
    private Navigator navigator;
    private List<Result> results;


    @Inject public ResultAdapter(Picasso picasso, Navigator navigator) {
        this.picasso = picasso;
        this.navigator = navigator;
    }


    public void setResults(List<Result> results) {
        this.results = results;
    }


    @Override
    public int getItemViewType(int position) {

        return results.get(position) != null ? VIEW_TYPE_ITEM : VIEW_TYPE_FOOTER;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == VIEW_TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_result, parent, false);

            return new ResultHolder(view);
        }
        else if (viewType == VIEW_TYPE_FOOTER) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.view_footer, parent, false);

            return new FooterHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ResultHolder) {
            final Context context = holder.itemView.getContext();
            final Result result = results.get(position);

            ResultHolder resultHolder = (ResultHolder) holder;
            resultHolder.title.setText(context.getString(R.string.name,
                    result.getName().getFirst(), result.getName().getLast()));
            setInfoText(resultHolder, result);
            picasso.load(result.getPicture().getMedium()).fit().into(resultHolder.resultImage);
            resultHolder.viewDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    navigator.navigateToDetail(context, result);
                }
            });
        }
        else if (holder instanceof FooterHolder) {
            FooterHolder footerHolder = (FooterHolder) holder;
            footerHolder.progressBar.setIndeterminate(true);
        }

    }

    private void setInfoText(ResultHolder holder, Result result) {
        StringBuilder sb = new StringBuilder();
        sb.append(result.getLocation().getCity())
                .append(", ")
                .append(result.getLocation().getState())
                .append(" | ")
                .append(result.getPhone());

        holder.info.setText(sb);
    }


    @Override
    public int getItemCount() {

        return results == null ? 0 : results.size();
    }

    public void addAll(List<Result> results) {
        this.results.addAll(results);
        notifyDataSetChanged();
    }

    public void clearAll() {
        if (!this.results.isEmpty()) {
            this.results.clear();
            notifyDataSetChanged();
        }
    }

    public void insertFooter() {
        results.add(null);
        notifyItemInserted(results.size() - 1);
    }

    public void removeFooter() {
        results.remove(results.size() - 1);
        notifyItemRemoved(results.size());
    }


    /**
     *  Result Item view
     */
    static class ResultHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.image_result)
        ImageView resultImage;
        @Bind(R.id.text_title)
        TextView title;
        @Bind(R.id.text_info)
        TextView info;
        @Bind(R.id.text_view)
        TextView viewDetail;

        ResultHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    /**
     * Footer progressbar view
     */
    static class FooterHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.footer)
        ProgressBar progressBar;

        FooterHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}