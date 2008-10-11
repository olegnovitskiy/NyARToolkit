/* 
 * PROJECT: NyARToolkit
 * --------------------------------------------------------------------------------
 * This work is based on the original ARToolKit developed by
 *   Hirokazu Kato
 *   Mark Billinghurst
 *   HITLab, University of Washington, Seattle
 * http://www.hitl.washington.edu/artoolkit/
 *
 * The NyARToolkit is Java version ARToolkit class library.
 * Copyright (C)2008 R.Iizuka
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this framework; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 * 
 * For further information please contact.
 *	http://nyatla.jp/nyatoolkit/
 *	<airmail(at)ebony.plala.or.jp>
 * 
 */
package jp.nyatla.nyartoolkit.core2.types.matrix;

import jp.nyatla.nyartoolkit.core.types.matrix.NyARDoubleMatrix33;


public class NyARFixedFloat24Matrix33 extends NyARI64Matrix33
{
	public void copyFrom(NyARDoubleMatrix33 i_matrix)
	{
		this.m00=(long)i_matrix.m00*0x1000000;
		this.m01=(long)i_matrix.m01*0x1000000;
		this.m02=(long)i_matrix.m02*0x1000000;
		this.m10=(long)i_matrix.m10*0x1000000;
		this.m11=(long)i_matrix.m11*0x1000000;
		this.m12=(long)i_matrix.m12*0x1000000;
		this.m20=(long)i_matrix.m20*0x1000000;
		this.m21=(long)i_matrix.m21*0x1000000;
		this.m22=(long)i_matrix.m22*0x1000000;
		return;
	}
	public static NyARFixedFloat24Matrix33[] createArray(int i_number)
	{
		NyARFixedFloat24Matrix33[] ret=new NyARFixedFloat24Matrix33[i_number];
		for(int i=0;i<i_number;i++)
		{
			ret[i]=new NyARFixedFloat24Matrix33();
		}
		return ret;
	}
}