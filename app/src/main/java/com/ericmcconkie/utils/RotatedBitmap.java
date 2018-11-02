package com.ericmcconkie.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;

public class RotatedBitmap {


    public static void drawRotatedImage(Bitmap source, float angle, Canvas canvas, Matrix matrix, Paint paint){
        matrix.reset();
        matrix.setRotate(angle,source.getWidth() / 2,source.getHeight() / 2);
        matrix.postTranslate(
                canvas.getWidth() / 2 - source.getWidth() / 2,
                canvas.getHeight() / 2 - source.getHeight() / 2
        );
        canvas.drawBitmap(source,matrix,paint);
    }

    public static void drawRotatedImage(Bitmap source, float angle, Canvas canvas,  Matrix matrix, Paint paint, Point point){
//        Matrix matrix = new Matrix();
        matrix.reset();
        matrix.setRotate(angle,source.getWidth() / 2,source.getHeight() / 2);

        matrix.postTranslate(
                (canvas.getWidth() / 2 - source.getWidth() / 2) + point.x,
                (canvas.getHeight() / 2 - source.getHeight() / 2) + point.y
        );

        canvas.drawBitmap(source,matrix,paint);
    }
}
