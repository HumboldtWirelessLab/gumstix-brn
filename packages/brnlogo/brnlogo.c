#include <stdio.h>
#include <unistd.h>
#include <directfb.h>

static IDirectFB *dfb = NULL;
static IDirectFBSurface *primary = NULL;
static IDirectFBSurface *tux = NULL;
static int screen_width  = 0;
static int screen_height = 0;

#define DFBCHECK(x...)                                         \
  {                                                            \
    DFBResult err = x;                                         \
                                                               \
    if (err != DFB_OK)                                         \
      {                                                        \
        fprintf( stderr, "%s <%d>:\n\t", __FILE__, __LINE__ ); \
        DirectFBErrorFatal( #x, err );                         \
      }                                                        \
  }

int main (int argc, char **argv)
{
  DFBSurfaceDescription   dsc;
  IDirectFBImageProvider *provider;

  int sprite_x, sprite_y, max_x, max_y;

  DFBCHECK (DirectFBInit (&argc, &argv));
  DFBCHECK (DirectFBCreate (&dfb));
  DFBCHECK (dfb->SetCooperativeLevel (dfb, DFSCL_FULLSCREEN));
  dsc.flags = DSDESC_CAPS;
  dsc.caps  = DSCAPS_PRIMARY | DSCAPS_FLIPPING;
  DFBCHECK (dfb->CreateSurface( dfb, &dsc, &primary ));
  DFBCHECK (primary->GetSize (primary, &screen_width, &screen_height));

  DFBCHECK (dfb->CreateImageProvider (dfb, /*DATADIR*/"/usr/share/brn/brnlogo.png", &provider));
  DFBCHECK (provider->GetSurfaceDescription (provider, &dsc));
  DFBCHECK (dfb->CreateSurface (dfb, &dsc, &tux));
  DFBCHECK (provider->RenderTo (provider, tux, NULL));
  provider->Release (provider);

  max_x = screen_width - dsc.width;
  max_y = screen_height - dsc.height;

  sprite_x = (screen_width - dsc.width) / 2;
  sprite_y = (screen_height - dsc.height) / 2;
    
  DFBCHECK (primary->FillRectangle (primary, 0, 0, screen_width, screen_height));
  DFBCHECK (primary->Blit (primary, tux, NULL, sprite_x, sprite_y));
  DFBCHECK (primary->Flip (primary, NULL, DSFLIP_WAITFORSYNC));

  sleep (5);
  
  tux->Release (tux);
  primary->Release (primary);
  dfb->Release (dfb);

  return 23;
}
