
package cn.backbone.tem.temchangfeng;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import java.util.Random;

public class StaticChartLineActivity extends Activity {

    private RelativeLayout static_chart_line_layout;
    private String title = "静态折线图";

    // 用于存放每条折线的点数据
    private XYSeries line1, line2;
    // 用于存放所有需要绘制的XYSeries
    private XYMultipleSeriesDataset mDataset;
    // 用于存放每条折线的风格
    private XYSeriesRenderer renderer1, renderer2;
    // 用于存放所有需要绘制的折线的风格
    private XYMultipleSeriesRenderer mXYMultipleSeriesRenderer;
    private GraphicalView chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_chart_line);
        static_chart_line_layout = (RelativeLayout) findViewById(R.id.static_chart_line_layout);
        initChart();
    }

    private void initChart() {
        // 初始化，必须保证XYMultipleSeriesDataset对象中的XYSeries数量和
        // XYMultipleSeriesRenderer对象中的XYSeriesRenderer数量一样多
        line1 = new XYSeries("折线1");
        line2 = new XYSeries("折线2");
        renderer1 = new XYSeriesRenderer();
        renderer2 = new XYSeriesRenderer();
        mDataset = new XYMultipleSeriesDataset();
        mXYMultipleSeriesRenderer = new XYMultipleSeriesRenderer();

        //对XYSeries和XYSeriesRenderer的对象的参数赋值
        initLine(line1);
        initLine(line2);
        initRenderer(renderer1, Color.GREEN, PointStyle.CIRCLE, true);
        initRenderer(renderer2, Color.CYAN, PointStyle.TRIANGLE, true);

        //将XYSeries对象和XYSeriesRenderer对象分别添加到XYMultipleSeriesDataset对象和XYMultipleSeriesRenderer对象中。
        mDataset.addSeries(line1);
        mDataset.addSeries(line2);
        mXYMultipleSeriesRenderer.addSeriesRenderer(renderer1);
        mXYMultipleSeriesRenderer.addSeriesRenderer(renderer2);

        //配置chart参数
        setChartSettings(mXYMultipleSeriesRenderer, "X", "Y", 0, 10, 0, 100, Color.RED,
                Color.WHITE);

        //通过该函数获取到一个View 对象
        chart = ChartFactory.getLineChartView(this, mDataset, mXYMultipleSeriesRenderer);

        //将该View 对象添加到layout中。
        static_chart_line_layout.addView(chart, new LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT));
    }

    private void initLine(XYSeries series) {
        // 随机生成两组数据
        Random r = new Random();
        for (int i = 0; i < 10; i++) {
            series.add(i, r.nextInt(100));
        }
    }

    private XYSeriesRenderer initRenderer(XYSeriesRenderer renderer, int color,
            PointStyle style, boolean fill) {
        // 设置图表中曲线本身的样式，包括颜色、点的大小以及线的粗细等
        renderer.setColor(color);
        renderer.setPointStyle(style);
        renderer.setFillPoints(fill);
        renderer.setLineWidth(1);
        return renderer;
    }

    protected void setChartSettings(XYMultipleSeriesRenderer mXYMultipleSeriesRenderer,
            String xTitle, String yTitle, double xMin, double xMax,
            double yMin, double yMax, int axesColor, int labelsColor) {
        // 有关对图表的渲染可参看api文档
        mXYMultipleSeriesRenderer.setChartTitle(title);
        mXYMultipleSeriesRenderer.setXTitle(xTitle);
        mXYMultipleSeriesRenderer.setYTitle(yTitle);
        mXYMultipleSeriesRenderer.setXAxisMin(xMin);
        mXYMultipleSeriesRenderer.setAxisTitleTextSize(30);
        mXYMultipleSeriesRenderer.setChartTitleTextSize(50);
        mXYMultipleSeriesRenderer.setLabelsTextSize(15);
        mXYMultipleSeriesRenderer.setXAxisMax(xMax);
        mXYMultipleSeriesRenderer.setYAxisMin(yMin);
        mXYMultipleSeriesRenderer.setYAxisMax(yMax);
        mXYMultipleSeriesRenderer.setAxesColor(axesColor);
        mXYMultipleSeriesRenderer.setLabelsColor(labelsColor);
        mXYMultipleSeriesRenderer.setShowGrid(true);
        mXYMultipleSeriesRenderer.setGridColor(Color.GRAY);
        mXYMultipleSeriesRenderer.setXLabels(20);
        mXYMultipleSeriesRenderer.setYLabels(10);
        mXYMultipleSeriesRenderer.setXTitle("time");
        mXYMultipleSeriesRenderer.setYLabelsAlign(Align.RIGHT);
        mXYMultipleSeriesRenderer.setPointSize((float) 5);
        mXYMultipleSeriesRenderer.setShowLegend(true);
        mXYMultipleSeriesRenderer.setLegendTextSize(20);
    }

}
