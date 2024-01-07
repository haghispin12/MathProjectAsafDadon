import com.amitai.mathprojectasafdadon.R;

public class MyFruitAdapter extends RecyclerView.Adapater<MyFruitAdapter.MyViewHolder> {

    public interface OnItemClickListener{
        void onItemClick(Fruit item);
    }

    private ArrayList<Fruit> fruits;
    private OnItemClickListener listener;

    public MyFruitsAdapater(ArrayList<Fruit> fruits, OnItemClickListener listener){
        this.fruits=fruits;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fruit, parent, false);
        return new MyViewHolder(view);
    }

}
