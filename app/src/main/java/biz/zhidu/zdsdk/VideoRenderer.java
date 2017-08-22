package biz.zhidu.zdsdk;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Java version of VideoRendererInterface.  In addition to allowing clients to
 * define their own rendering behavior (by passing in a Callbacks object), this
 * class also provides a createGui() method for creating a GUI-rendering window
 * on various platforms.
 */
public class VideoRenderer {

  /** Java version of cricket::VideoFrame. */
  public static class I420Frame {
    public final int width;
    public final int height;
    public final int[] yuvStrides;
    public final ByteBuffer[] yuvPlanes;

    /**
     * Construct a frame of the given dimensions with the specified planar
     * data.  If |yuvPlanes| is null, new planes of the appropriate sizes are
     * allocated.
     */
    public I420Frame(
        int width, int height, int[] yuvStrides, ByteBuffer[] yuvPlanes) {
      this.width = width;
      this.height = height;
      this.yuvStrides = yuvStrides;
      if (yuvPlanes == null) {
        yuvPlanes = new ByteBuffer[3];
        yuvPlanes[0] = ByteBuffer.allocateDirect(yuvStrides[0] * height);
        yuvPlanes[1] = ByteBuffer.allocateDirect(yuvStrides[1] * height);
        yuvPlanes[2] = ByteBuffer.allocateDirect(yuvStrides[2] * height);
      }
      this.yuvPlanes = yuvPlanes;
    }

    /**
     * Copy the planes out of |source| into |this| and return |this|.  Calling
     * this with mismatched frame dimensions is a programming error and will
     * likely crash.
     */
    public I420Frame copyFrom(I420Frame source) {
      if (!Arrays.equals(yuvStrides, source.yuvStrides) ||
          width != source.width || height != source.height) {
        throw new RuntimeException("Mismatched dimensions!  Source: " +
            source.toString() + ", destination: " + toString());
      }
      copyPlane(source.yuvPlanes[0], yuvPlanes[0]);
      copyPlane(source.yuvPlanes[1], yuvPlanes[1]);
      copyPlane(source.yuvPlanes[2], yuvPlanes[2]);
      return this;
    }

    @Override
    public String toString() {
      return width + "x" + height + ":" + yuvStrides[0] + ":" + yuvStrides[1] +
          ":" + yuvStrides[2];
    }

    // Copy the bytes out of |src| and into |dst|, ignoring and overwriting
    // positon & limit in both buffers.
    private void copyPlane(ByteBuffer src, ByteBuffer dst) {
      src.position(0).limit(src.capacity());
      dst.put(src);
      dst.position(0).limit(dst.capacity());
    }
    
	public static I420Frame createBlackImage(int w, int h)
	{
		int fSize = w * h;
		ByteBuffer[] yuvPlanes = new ByteBuffer[3];
		yuvPlanes[0] = ByteBuffer.allocate(fSize);
		yuvPlanes[1] = ByteBuffer.allocate(fSize/4);
		yuvPlanes[2] = ByteBuffer.allocate(fSize/4);
		
		for(int i=0; i<fSize; i++)
		{
			yuvPlanes[0].put((byte)16);
		}
		fSize /= 4;
		for(int i=0; i<fSize; i++)
		{
			yuvPlanes[1].put((byte)128);
			yuvPlanes[2].put((byte)128);
		}
		int[] yuvStrides = new int[3];
		yuvStrides[0] = w;
		yuvStrides[1] = yuvStrides[2] = w / 2;
		return new I420Frame(w, h, yuvStrides, yuvPlanes);
	}
}

  /** The real meat of VideoRendererInterface. */
  public static interface Callbacks {
    public void setSize(int width, int height);
    public void renderFrame(I420Frame frame);
  }

  // |this| either wraps a native (GUI) renderer or a client-supplied Callbacks
  // (Java) implementation; so exactly one of these will be non-0/null.
  final long nativeVideoRenderer;
  private final Callbacks callbacks;

  public static VideoRenderer createGui(int x, int y) {
    long nativeVideoRenderer = nativeCreateGuiVideoRenderer(x, y);
    if (nativeVideoRenderer == 0) {
      return null;
    }
    return new VideoRenderer(nativeVideoRenderer);
  }

  public VideoRenderer(Callbacks callbacks) {
    nativeVideoRenderer = nativeWrapVideoRenderer(callbacks);
    this.callbacks = callbacks;
  }

  private VideoRenderer(long nativeVideoRenderer) {
    this.nativeVideoRenderer = nativeVideoRenderer;
    callbacks = null;
  }

  public void dispose() {
    free(nativeVideoRenderer);
  }

  private static native long nativeCreateGuiVideoRenderer(int x, int y);
  private static native long nativeWrapVideoRenderer(Callbacks callbacks);

  private static native void free(long nativeVideoRenderer);
}
