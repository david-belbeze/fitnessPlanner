package planner.fitness.com.fitnessplanner.view.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class SpaceDecoration extends RecyclerView.ItemDecoration {

    private int space;

    public SpaceDecoration(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int adapterPosition = parent.getChildAdapterPosition(view);

        if (adapterPosition == 0) {
            outRect.set(space, space, space, space);
        } else {
            outRect.set(space, 0, space, space);
        }

        super.getItemOffsets(outRect, view, parent, state);
    }
}
