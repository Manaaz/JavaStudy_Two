package lesson5;

public class CalcModifyArr {

    static float[] arr;

    public CalcModifyArr(float[] arr) {
        this.arr = arr;
    }

    public float[] getArr() {
        return arr;
    }

    public void calculateArrNumbers() {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }
}
