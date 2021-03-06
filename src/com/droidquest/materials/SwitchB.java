package com.droidquest.materials;

import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.items.Sentry;

public class SwitchB extends Switch 
{
transient SwitchA switchA=null;
boolean flipped=false;
transient Sentry sentry=null;

public SwitchB() 
  {
	super(Switch.ROT_LEFT);
  }

public void TouchedByItem(Item item) 
  {
	value=true;
	for (int a=0; a<level.materials.size(); a++)
	  {
	     Material mat = (Material) level.materials.elementAt(a);
	     if (mat.getClass().toString().endsWith("SwitchA"))
	       switchA = (SwitchA) mat;
	  }
	for (int a=0; a<level.items.size(); a++)
	  {
	     Item item2 = (Item) level.items.elementAt(a);
	     if (item2.room==item.room)
	       if (item2.getClass().toString().endsWith("Sentry"))
		 sentry = (Sentry) item2;
	  }
  }

public void Animate() 
  {
	super.Animate();
	if (!flipped)
	  if (value)
	    {
	       if (switchA!=null)
		 if (switchA.value)
		   {
		      flipped=true;
		      TextBox textbox = (TextBox) sentry.room.textBoxes.elementAt(0);
		      textbox.textString="KLAATA BARADA NIKTO!";
		      textbox.y=6*32;
		      textbox.width = 400;
		      level.items.removeElement(sentry);
		   }
	    }
  }

}