package sofia.media.comp;

import java.util.Map;

import android.media.AudioFormat;
import android.media.AudioTrack;

public class AudioFileFormat extends Object{
	
	public static enum Type
	{
		WAV ("", ".wav"),
		MP3 ("", ".mp3");
		
		private String name;
		private String extension;
		
		Type(String name, String extension)
		{
			this.name= name;
			this.extension = extension;
		}
		
		String getExtension()
		{
			return extension;
		}
	}
	
	AudioTrack audTrack;
	int frameLength;
	AudioFileFormat.Type type;
	Map<String,Object> properties;
	int byteLength;
	int numFrames;
	
	/**
	 * Constructs an audio file format object.
	 * 
	 * @param type
	 * @param audTrack
	 * @param frameLength
	 */
	public AudioFileFormat(AudioFileFormat.Type type, AudioTrack audTrack, int frameLength) 
	{
		this.audTrack = audTrack;
		this.type = type;
		this.frameLength = frameLength;
		properties = null;
		numFrames = frameLength / audTrack.getChannelConfiguration();
		int bytesPerSample = 2;
		byteLength = bytesPerSample * numFrames;
	}
	
	
   /**
    *  Construct an audio file format object with a set of defined properties.
    *   
    * @param type
    * @param format
    * @param frameLength
    * @param properties
    */
	public AudioFileFormat(AudioFileFormat.Type type, AudioTrack audTrack, int frameLength, Map<String,Object> properties) 
	{
		this.type = type;
		this.audTrack = audTrack;
		this.frameLength = frameLength;
		this.properties = properties;
		numFrames = frameLength / audTrack.getChannelConfiguration();
		int bytesPerSample = 2;
		byteLength = bytesPerSample * numFrames;
	}
	
	/**
	 * Constructs an audio file format object.
	 * @param type
	 * @param byteLength
	 * @param format
	 * @param frameLength
	 */
	protected AudioFileFormat(AudioFileFormat.Type type, int byteLength, AudioTrack audTrack, int frameLength)
	{
		this.type = type;
		this.byteLength = byteLength;
		this.audTrack = audTrack;
		this.frameLength = frameLength;
		this.properties = null;
		
	}
	
	/**
	 * Obtains the size in bytes of the entire audio file (not just its audio data).
	 * @return
	 */
	public int getByteLength() 
	{
		return byteLength;
	}
    
	/**
	 * Obtains the format of the audio data contained in the audio file.
	 * @return
	 */
	public AudioTrack getFormat() 
	{
		return audTrack;
	}
    
	/**
	 * Obtains the length of the audio data contained in the file, expressed in sample frames.
	 * @return
	 */
	public int getFrameLength() 
	{
		return frameLength;
	}
	
	public int getFrameSize()
	{
		return numFrames;
	}
    
	/**
	 * Obtain the property value specified by the key.
	 * @param key
	 * @return
	 */
	public Object getProperty(String key) 
	{
		if (properties != null)
		{
			return properties.get(key);
		}
		return "No properties available.";
	}
    
	/**
	 * Obtains the audio file type, such as WAVE MP3.
	 * @return
	 */
	public AudioFileFormat.Type	getType() 
	{
		return type;
	}
    
	/**
	 * Obtain an unmodifiable map of properties.
	 * @return
	 */
	public Map<String,Object> properties() 
	{
		return properties;
	}
    
	/**
	 * Provides a string representation of the file format.
	 * @return
	 */
	@Override
	public String toString() 
	{
		return "AudioFileFormat Type: " + type +", ByteLength: "+byteLength+
				", AudioTrack: "+audTrack.toString()+"FrameLength: "+frameLength+
				", Map: "+properties;
		
	}
    

}
