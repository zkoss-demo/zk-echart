package zk;

import org.icepear.echarts.Bar;
import org.icepear.echarts.render.Engine;
import org.zkoss.zk.ui.*;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.Iframe;

import java.util.Random;

/**
 * reference https://echarts.icepear.org/#/quick-start
 */
public class StartComposer extends SelectorComposer {
    private String currentFolder;
    private Engine engine = new Engine();
    private Bar bar;

    @Wire("iframe")
    private Iframe iframe;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        currentFolder = WebApps.getCurrent().getRealPath("");
        renderBar();
    }

    private void renderBar() {
        bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[] { "Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie" })
                .addYAxis()
                .addSeries("2015", new Number[] { 43.3, 83.1, 86.4, 72.4 })
                .addSeries("2016", new Number[] { 85.8, 73.4, 65.2, 53.9 })
                .addSeries("2017", new Number[] { 93.7, 55.1, 82.5, 39.1 });
        // The render method will generate our EChart into a HTML file saved locally in the current directory.
        // The name of the HTML can also be set by the first parameter of the function.
        engine.render(currentFolder+ "/index.html", bar);
    }

    @Listen("onClick = #update")
    public void update(){
        bar = new Bar()
                .setLegend()
                .setTooltip("item")
                .addXAxis(new String[] { "Matcha Latte", "Milk Tea", "Cheese Cocoa", "Walnut Brownie" })
                .addYAxis()
                .addSeries("2015", produceNumber())
                .addSeries("2016", produceNumber())
                .addSeries("2017", produceNumber());
        engine.render(currentFolder+ "/index.html", bar);
        iframe.invalidate(); //reload updated html
    }

    private Number[] produceNumber() {
        Random random = new Random();
        return new Number[] { random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200) };
    }
}
