import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;

import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.MaryAudioUtils;

import org.apache.commons.cli.*;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

public class maryTTSExample {

    public static void main(String[] args) throws MaryConfigurationException, marytts.exceptions.SynthesisException, IOException {
        long beforeUsedMem = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        var start = System.currentTimeMillis();
        MaryInterface marytts = new LocalMaryInterface();
        AudioInputStream audio = marytts.generateAudio("The 1881 world tour of King Kalākaua made him the first monarch to circumnavigate the globe. His agenda was to negotiate contract labor for the Kingdom of Hawaiis sugar plantations, with hopes of saving the dwindling Native Hawaiian population by drawing immigration from Asia-Pacific nations. Rumors circulated that the King secretly intended to use the trip to sell the Hawaiian Islands. He visited American legislators, had an audience with Pope Leo XIII in Rome, and met with European and Asian heads of state. In between negotiations, Kalākaua and his companions visited tourist sites and attended local Masonic lodge meetings. As a result of his visit with Thomas Edison, Iolani Palace became the first building in Hawaii with electric lighting.");
        MaryAudioUtils.writeWavFile(MaryAudioUtils.getSamplesAsDoubleArray(audio), "R:\\Extracted\\audioOutput2.wav", audio.getFormat());  //replace the file path according to your file/location.
        var end = System.currentTimeMillis();
        float timeConsumed = (float)(end-start) / (float)1000;
        long afterUsedMem=Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.println("Memory Consumption: "+ (afterUsedMem-beforeUsedMem));
        System.out.println("Time Consumption: "+ timeConsumed);
    }
}