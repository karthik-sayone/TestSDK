package com.test.mapviewjava;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.peterlaurence.mapview.MapView;
import com.peterlaurence.mapview.MapViewConfiguration;
import com.peterlaurence.mapview.core.TileStreamProvider;
import com.peterlaurence.mapview.api.MarkerApiKt;
import com.peterlaurence.mapview.paths.PathPoint;
import com.peterlaurence.mapview.paths.PathView;
import com.peterlaurence.mapview.paths.PathViewKt;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

public class MapViewWrapper extends LinearLayout {

    MapView mMapView;

    public MapViewWrapper(Context context) {
        super(context);
//        initMapView(context);
    }

    public MapViewWrapper(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initMapView(context);
    }

    public MapViewWrapper(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
//        initMapView(context);
    }

    /*public void changeConfig(final Context context, String file){
        TileStreamProvider tileStreamProvider = new TileStreamProvider() {
            @Override
            public InputStream getTileStream(int row, int col, int zoomLvl) {
                try {
                    return context.getAssets().open("tiles/esp/"+zoomLvl+"/"+row+"/"+col+".jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        final MapViewConfiguration config = new MapViewConfiguration(
                5, 8192, 8192, 256, tileStreamProvider
        ).setMaxScale(2f);

        BuildersKt.launch(
                GlobalScope.INSTANCE,
                Dispatchers.getMain(),//context to be ran on
                CoroutineStart.DEFAULT,
                new Function2<CoroutineScope, Continuation<? super Unit>, Unit*//*or your return type here*//*>() {
                    @Override
                    public Unit*//*or your return type here*//* invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        //do what you want
                        mMapView.configure(config);
                        return Unit.INSTANCE; //or something with the defined type
                    }
                }
        );
    }
*/
    public void initMapView(final Context context, final String file) {

        TileStreamProvider tileStreamProvider = new TileStreamProvider() {
            @Override
            public InputStream getTileStream(int row, int col, int zoomLvl) {
                try {
                    return context.getAssets().open(/*"tiles/esp/"*/file + zoomLvl + "/" + row + "/" + col + ".jpg");
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        };
        int tileSize = 256;
        final MapViewConfiguration config = new MapViewConfiguration(
                5, 8192, 8192, tileSize, tileStreamProvider
        ).setMaxScale(2f).enableRotation(true);
        mMapView = new MapView(context);

        BuildersKt.launch(
                GlobalScope.INSTANCE,
                Dispatchers.getMain(),//context to be ran on
                CoroutineStart.DEFAULT,
                new Function2<CoroutineScope, Continuation<? super Unit>, Unit/*or your return type here*/>() {
                    @Override
                    public Unit/*or your return type here*/ invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        //do what you want
                        mMapView.configure(config);
                        mMapView.defineBounds(0.0, 0.0, 1.0, 1.0);
                        return Unit.INSTANCE; //or something with the defined type
                    }
                }
        );

        addView(mMapView);
    }

    public void addNewMarker(final Context context) {

        BuildersKt.launch(
                GlobalScope.INSTANCE,
                Dispatchers.getMain(),//context to be ran on
                CoroutineStart.DEFAULT,
                new Function2<CoroutineScope, Continuation<? super Unit>, Unit/*or your return type here*/>() {
                    @Override
                    public Unit/*or your return type here*/ invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        //do what you want
                        ImageView marker1 = new ImageView(context);
                        marker1.setImageResource(R.drawable.map_marker);
                        ImageView marker2 = new ImageView(context);
                        marker2.setImageResource(R.drawable.map_marker);
//        marker.setImageBitmap();
                        MarkerApiKt.addMarker(mMapView, marker1, .5, .5, -0.5f, -1f, 0f, 0f);
                        MarkerApiKt.addMarker(mMapView, marker2, .2, .7, -0.5f, -1f, 0f, 0f);
                        return Unit.INSTANCE; //or something with the defined type
                    }
                }
        );
    }

    public void addpath(final Context context) {
        BuildersKt.launch(
                GlobalScope.INSTANCE,
                Dispatchers.getMain(),//context to be ran on
                CoroutineStart.DEFAULT,
                new Function2<CoroutineScope, Continuation<? super Unit>, Unit/*or your return type here*/>() {
                    @Override
                    public Unit/*or your return type here*/ invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        //do what you want
                        PathView pathView = new PathView(context);
                        PathViewKt.addPathView(mMapView, pathView);

                        Iterable<float[]> a = Arrays.asList(
                                PathViewKt.toFloatArray(
                                        Arrays.asList(
                                                new PathPoint(0.2, 0.3),
                                                new PathPoint(0.25, 0.15),
                                                new PathPoint(0.32, 0.1),
                                                new PathPoint(0.427, 0.212),
                                                new PathPoint(0.6, 0.15),
                                                new PathPoint(0.67, 0.1)
                                        ),
                                        mMapView),
                                PathViewKt.toFloatArray(
                                        Arrays.asList(
                                                new PathPoint(0.5, 0.5),
                                                new PathPoint(0.55, 0.52),
                                                new PathPoint(0.57, 0.54),
                                                new PathPoint(0.59, 0.52),
                                                new PathPoint(0.6, 0.51),
                                                new PathPoint(0.59, 0.5),
                                                new PathPoint(0.578, 0.447),
                                                new PathPoint(0.46, 0.44),
                                                new PathPoint(0.5, 0.5)
                                        ),
                                        mMapView));

                        List<PathView.DrawablePath> destinationPath = new ArrayList<>();
                        int i=0;
                        for (Object obj : a) {
                            i++;
                            float[] data = (float[]) obj;
                            PathView.DrawablePath drawablePath = new DrawablePath(true, data, null, null);
                            destinationPath.add(drawablePath);
                        }
                        pathView.updatePaths(destinationPath);
                        return Unit.INSTANCE; //or something with the defined type
                    }
                }
        );
    }

}

class DrawablePath implements PathView.DrawablePath {

    int offset = 0;
    Paint mPaint;
    float[] path;
    Float width;
    boolean visible;


    DrawablePath(Boolean visible, float[] path, Paint paint, Float width) {
        this.visible = visible;
        this.path = path;
        mPaint = paint;
        this.width = width;
    }

    @Override
    public int getCount() {
        return path.length;
    }

    @Override
    public int getOffset() {
        return offset;
    }

    @Nullable
    @Override
    public Paint getPaint() {
        return mPaint;
    }

    @Override
    public void setPaint(@Nullable Paint paint) {
        mPaint = paint;
    }

    @NotNull
    @Override
    public float[] getPath() {
        return path;
    }

    @Override
    public void setPath(@NotNull float[] floats) {
        path = floats;
    }

    @Override
    public boolean getVisible() {
        return visible;
    }

    @Nullable
    @Override
    public Float getWidth() {
        return width;
    }
}
