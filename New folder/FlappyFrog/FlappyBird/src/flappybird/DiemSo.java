/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flappybird;

/**
 *
 * @author Hieu
 */
public class DiemSo {

    private static int diem = 0;
    private ConChim conchimD = new ConChim();
    private OngNuoc ongnuocD = new OngNuoc();
    private static boolean bl1 = false;
    private static boolean bl2 = false;
    private static boolean bl3 = false;
    private static boolean bl4 = false;

    //lay gia tri diem
    public static int getDiem() {
        return diem;
    }

    //cong diem khi qua moi ong nuoc
    public void congdiem() {
        //qua ong nuoc thu 1
        if (conchimD.getX() + 45 > ongnuocD.getX1() && conchimD.getX() < ongnuocD.getX1() + 65) {
            bl1 = true;
        }
        if (bl1 == true && conchimD.getX() > ongnuocD.getX1() + 65) {
//            diem = diem +1;
            diem++;
            bl1 = false;
        }
        //qua ong nuoc thu 2
        if (conchimD.getX() + 45 > ongnuocD.getX2() && conchimD.getX() < ongnuocD.getX2() + 65) {
            bl2 = true;
        }
        if (bl2 == true && conchimD.getX() > ongnuocD.getX2() + 65) {
//            diem = diem +1;
            diem++;
            bl2 = false;
        }
        //qua ong nuoc thu 3
        if (conchimD.getX() + 45 > ongnuocD.getX3() && conchimD.getX() < ongnuocD.getX3() + 65) {
            bl3 = true;
        }
        if (bl3 == true && conchimD.getX() > ongnuocD.getX3() + 65) {
//            diem = diem +1;
            diem++;
            bl3 = false;
        }
        //qua ong nuoc thu 4
        if (conchimD.getX() + 45 > ongnuocD.getX4() && conchimD.getX() < ongnuocD.getX4() + 65) {
            bl4 = true;
        }
        if (bl4 == true && conchimD.getX() > ongnuocD.getX4() + 65) {
//            diem = diem +1;
            diem++;
            bl4 = false;
        }
    }
}
