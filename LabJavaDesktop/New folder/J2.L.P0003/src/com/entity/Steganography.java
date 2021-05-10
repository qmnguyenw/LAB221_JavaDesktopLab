/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author vietddse62677
 */
public class Steganography {

    /*
        Empty constructor
     */
    public Steganography() {

    }

    /*  
        *Encrypt an image with text and return the encoded image
        *@param img_ori: the image to encode the message inside it
        *@param message: the message that user what to encode into picture
     */
    public BufferedImage encode(BufferedImage img_ori, String message) {
        BufferedImage image = userSpace(img_ori);
        image = addText(image, message);
        return image;
    }

    /*
        *Decrypt the image of type .png, extracts the hidden text inside the image
        *@param image: the image to extract thge message from
     */
    public String decode(BufferedImage image) {
        byte[] decode;
        try {
            decode = decodeText(getByteData(image));
            return (new String(decode));
        } catch (Exception e) {
            JOptionPane.
                    showMessageDialog(
                            null,
                            "There is no hidden message in this image",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            return "";
        }
    }

    /*
        *Set method to save an image file
        *@param image: the image file to save
        *@param file: the file to save image to
        *@param ext: the extension(format) of the file to be saved
     */
    public boolean setImage(BufferedImage image, File file, String ext) {
        try {
            file.delete();
            ImageIO.write(image, ext, file);
            return true;
        } catch (Exception ex) {
            JOptionPane.
                    showMessageDialog(
                            null,
                            "This file could not be saved!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    /*
        *Handles the addition of text into an image
        *@param image: the image to add hidden text to
        *@param text: the text to hide in the image
     */
    private BufferedImage addText(BufferedImage image, String text) {
        //convert all items to byte arrays: image, message, message length
        byte img[] = getByteData(image);
        byte msg[] = text.getBytes();
        byte len[] = bitConversion(msg.length);
        //the first 4 byte in the image will contain the length of the message
        try {
            encodeText(img, len, 0); //0 first position
            encodeText(img, msg, 32); //4 bytes of space for length: 4bytes * 8 bits = 32 bits
        } catch (Exception e) {
            JOptionPane.
                    showMessageDialog(
                            null,
                            "Target File cannot hold message!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
        }
        return image;
    }

    /*
        *Simply copy the image 
        *@param image: the image to copy, removes compression
     */
    private BufferedImage userSpace(BufferedImage image) {
        BufferedImage newImg = new BufferedImage(
                image.getWidth(),
                image.getHeight(),
                BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D graphics = newImg.createGraphics();
        graphics.drawRenderedImage(image, null);
        graphics.dispose();
        return newImg;
    }

    /*
        *Gets the byte array of an image
        *@param image The image to get byte data from
        *@return Returns the byte array of the image supplied
     */
    private byte[] getByteData(BufferedImage image) {
        WritableRaster raster = image.getRaster();
        DataBufferByte buffer = (DataBufferByte) raster.getDataBuffer();
        return buffer.getData();
    }

    /*
        *Gernerates proper byte format of an integer
        *@param i The integer to convert
        *@return Returns a byte[4] array converting the supplied integer into bytes
     */
    private byte[] bitConversion(int i) {
        //only using 4 bytes
        byte byte3 = (byte) ((i & 0xFF000000) >>> 24);
        byte byte2 = (byte) ((i & 0x00FF0000) >>> 16);
        byte byte1 = (byte) ((i & 0x0000FF00) >>> 8);
        byte byte0 = (byte) ((i & 0x000000FF));
        return (new byte[]{byte3, byte2, byte1, byte0});
    }

    /*
        *Encode an array of bytes into another array of bytes at a supplied offset
        *@param image   Array of data representing an image
        *@param addition    Array of data to add to the supplied image data array
        *@param offset  The offset into the image array to add the adddition data
        *@param Returns data Array of Merged image and addtion data
     */
    private byte[] encodeText(byte[] image, byte[] addition, int offset) {
        //check that the data + offset will fit in the image
        if (addition.length + offset > image.length) {
            throw new IllegalArgumentException("File not long enough");
        }
        //loop through each addition byte
        for (int i = 0; i < addition.length; ++i) {
            //loop through the 8 bits of each byte
            int add = addition[i];
            for (int bit = 7; bit >= 0; --bit, ++offset) {
                //assign an integer to b, shifted by bit spaces AND 1
                int b = (add >>> bit) & 1;
                //changes the last bit of the byte in the image to be the bit of addition
                image[offset] = (byte) ((image[offset] & 0xFE) | b);
            }
        }
        return image;
    }

    /*
        *Retrieves hidden text from an image
        *@param image Array of data, representing an image
        *@returns Array of data which contrains the hidden text
     */
    private byte[] decodeText(byte[] image) {
        int length = 0;
        int offset = 32;
        //loop throught 32 bytes of data to determine text length
        for (int i = 0; i < 32; i++) {
            length = (length << 1) | (image[i] & 1);
        }
        byte[] result = new byte[length];
        //loop throught each byte of text
        for (int b = 0; b < result.length; b++) {
            //boop throught each bit within a byte of text
            for (int i = 0; i < 8; i++, offset++) {
                //assign bit: [(new byte value) << 1] OR [(text byte) AND 1]
                result[b] = (byte) ((result[b] << 1) | (image[offset] & 1));
            }
        }
        return result;
    }
}
