package com.example.paintktapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

// Viewを継承したクラス
class MyView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var path : Path = Path() //線を引く，図形を描く，グラフィック描画
    private var paint : Paint = Paint() //色とか太さ
    private var drawX : Float = 0F
    private var drawY : Float = 0F

    // onDraw(描画の準備)
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        paint.color = Color.RED //色
        paint.style = Paint.Style.STROKE //描画のスタイルの線にする
        paint.strokeWidth = 5F //幅
        canvas?.drawPath(path, paint)

    }
    //実際の描画 （押した時，動かした時）
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        //タッチポジションを取得
        drawX = event!!.x
        drawY = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN -> path.moveTo(drawX,drawY)
            MotionEvent.ACTION_MOVE -> path.lineTo(drawX,drawY)
        }
        //再描画を実行させる呪文
        invalidate()

        //return super.onTouchEvent(event)
        return true
    }
    // クリア処理
    fun clearCanvas(){
        path.reset()
        invalidate()
    }
}