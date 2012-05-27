/* 
 * PROJECT: NyARToolkit JOGL sample program.
 * --------------------------------------------------------------------------------
 * The MIT License
 * Copyright (c) 2008 nyatla
 * airmail(at)ebony.plala.or.jp
 * http://nyatla.jp/nyartoolkit/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * 
 */
package jp.nyatla.nyartoolkit.jogl.sample.sketch;

import javax.media.opengl.*;
import jp.nyatla.nyartoolkit.core.NyARException;
import jp.nyatla.nyartoolkit.jmf.utils.*;
import jp.nyatla.nyartoolkit.jogl.sketch.GlSketch;
import jp.nyatla.nyartoolkit.jogl.utils.*;
import jp.nyatla.nyartoolkit.markersystem.NyARMarkerSystemConfig;



/**
 * JMFからの映像入力からマーカ2種を検出し、そこに立方体を重ねます。
 * ARマーカには、patt.hiro/patt.kanjiを使用して下さい。
 */
public class SimpleLiteM extends GlSketch
{
	private NyARJmfCamera camera;
	private NyARGlMarkerSystem nyar;
	private NyARGlRender render;	
	public void setup(GL gl)throws NyARException
	{
		this.size(640,480);
		NyARMarkerSystemConfig config = new NyARMarkerSystemConfig(640,480);
		JmfCaptureDeviceList devlist = new JmfCaptureDeviceList();
		JmfCaptureDevice d = devlist.getDevice(0);
		d.setCaptureFormat(config.getScreenSize(),30.0f);
		this.camera=new NyARJmfCamera(d);//create sensor system
		this.nyar=new NyARGlMarkerSystem(config);   //create MarkerSystem
		this.render=new NyARGlRender(this.nyar);
		
		this.ids[0]=this.nyar.addARMarker(ARCODE_FILE2,16,25,80);
		this.ids[1]=this.nyar.addARMarker(ARCODE_FILE,16,25,80);
		gl.glEnable(GL.GL_DEPTH_TEST);
		this.camera.start();
	}
	private final static String ARCODE_FILE = "../../Data/patt.hiro";
	private final static String ARCODE_FILE2 = "../../Data/patt.kanji";
	private int[] ids=new int[2];
	
	public void draw(GL gl)
	{
		synchronized(this.camera){
			try {
				this.render.drawBackground(gl, this.camera.getSourceImage());
				this.render.loadARProjectionMatrix(gl);
				this.nyar.update(this.camera);
				if(this.nyar.isExistMarker(this.ids[0])){
					this.render.loadMarkerMatrix(gl,this.ids[0]);
					this.render.colorCube(gl,40,0,0,20);
				}
				if(this.nyar.isExistMarker(this.ids[1])){
					this.render.loadMarkerMatrix(gl,this.ids[1]);
					this.render.colorCube(gl,40,0,0,20);
				}
				Thread.sleep(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}	
	public static void main(String[] args)
	{
		try {
			new SimpleLiteM();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return;
	}


}