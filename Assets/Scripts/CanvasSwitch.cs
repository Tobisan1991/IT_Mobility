using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CanvasSwitch : MonoBehaviour {
    public GameObject cnv1;
    public GameObject cnv2;
    

    public void aktivierer()
    {
        cnv1.SetActive(false);
        cnv2.SetActive(true);
    }
   
}
