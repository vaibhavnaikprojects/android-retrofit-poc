package com.kushalnawlakhaprojects.buyit.adapters;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kushalnawlakhaprojects.buyit.activities.R;
import com.kushalnawlakhaprojects.buyit.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by vanaik on 2/7/2017.
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private Context mContext;
    private List<Product> productList;
    OnItemClickListener mItemClickListener;

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView productImage;
        private TextView productName;
        private TextView brandName;
        private TextView price;
        private TextView originalPrice;
        private TextView percentOff;
        /*private ImageView overflow;*/
        public ViewHolder(View itemView) {
            super(itemView);
            productImage=(ImageView)itemView.findViewById(R.id.product_image);
            productName=(TextView)itemView.findViewById(R.id.product_name);
            brandName=(TextView)itemView.findViewById(R.id.product_brand);
            price=(TextView)itemView.findViewById(R.id.product_price);
            originalPrice=(TextView)itemView.findViewById(R.id.product_original_price);
            percentOff=(TextView) itemView.findViewById(R.id.product_percent_off);
            /*overflow=(ImageView) itemView.findViewById(R.id.overflow);*/
            productImage.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(itemView,getPosition());
            }
        }
    }

    public ProductAdapter(Context mContext, List<Product> productList){
        this.mContext=mContext;
        this.productList=productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout, parent, false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder,final int position) {
        final Product product= productList.get(position);
        holder.productName.setText(product.getProductName());
        holder.brandName.setText("by " + product.getBrandName());
        Picasso.with(mContext).load(product.getThumbnailImageUrl()).into(holder.productImage);
        holder.price.setText(product.getPrice());
        if(!product.getPrice().equalsIgnoreCase(product.getOriginalPrice())) {
            holder.originalPrice.setText(product.getOriginalPrice());
            holder.originalPrice.setPaintFlags(holder.originalPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.percentOff.setText(product.getPercentOff()+" Off");
        }
        /*holder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "popup clicked", Toast.LENGTH_SHORT).show();
            }
        });*/
    }


    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

}
