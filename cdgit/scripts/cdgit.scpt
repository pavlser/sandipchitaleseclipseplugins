FasdUAS 1.101.10   ��   ��    k             l      ��  ��   #

Given a path to a file or a folder this script will cd a terminal window
to the git repository root folder i.e. the one that contains the .git folder.

Save this script in /Library/Scripts folder.

You can use it from Eclipse by configuring an external tool configuration.

Name:      cdgit
Location:  /usr/bin/osascript
Arguments: /Library/Scripts/cdgit.scpt ${resource_loc}
Unselect 'Refresh resources upon completion.'
Unselect 'Build before launch'
You may want to select Diplay in favorites menu option also.

Author: Sandip Chitale

     � 	 	: 
 
 G i v e n   a   p a t h   t o   a   f i l e   o r   a   f o l d e r   t h i s   s c r i p t   w i l l   c d   a   t e r m i n a l   w i n d o w 
 t o   t h e   g i t   r e p o s i t o r y   r o o t   f o l d e r   i . e .   t h e   o n e   t h a t   c o n t a i n s   t h e   . g i t   f o l d e r . 
 
 S a v e   t h i s   s c r i p t   i n   / L i b r a r y / S c r i p t s   f o l d e r . 
 
 Y o u   c a n   u s e   i t   f r o m   E c l i p s e   b y   c o n f i g u r i n g   a n   e x t e r n a l   t o o l   c o n f i g u r a t i o n . 
 
 N a m e :             c d g i t 
 L o c a t i o n :     / u s r / b i n / o s a s c r i p t 
 A r g u m e n t s :   / L i b r a r y / S c r i p t s / c d g i t . s c p t   $ { r e s o u r c e _ l o c } 
 U n s e l e c t   ' R e f r e s h   r e s o u r c e s   u p o n   c o m p l e t i o n . ' 
 U n s e l e c t   ' B u i l d   b e f o r e   l a u n c h ' 
 Y o u   m a y   w a n t   t o   s e l e c t   D i p l a y   i n   f a v o r i t e s   m e n u   o p t i o n   a l s o . 
 
 A u t h o r :   S a n d i p   C h i t a l e 
 
   
�� 
 i         I     �� ��
�� .aevtoappnull  �   � ****  o      ���� 0 argv  ��    k     d       r         n         4    �� 
�� 
cobj  m    ����   o     ���� 0 argv    o      ���� 0 cdto cdTo      r    
    m    ��
�� boovfals  o      ���� (0 terminalwasrunning terminalWasRunning      O    "    Z    !  ����  I   ��  ��
�� .coredoexbool       obj    4    �� !
�� 
prcs ! m     " " � # #  T e r m i n a l��    r     $ % $ m    ��
�� boovtrue % o      ���� (0 terminalwasrunning terminalWasRunning��  ��    m     & &�                                                                                  sevs  alis    �  Macintosh HD               ��*H+   .�System Events.app                                               /�d�7��        ����  	                CoreServices    �
�      �8'7     .� .�� .��  :Macintosh HD:System:Library:CoreServices:System Events.app  $  S y s t e m   E v e n t s . a p p    M a c i n t o s h   H D  -System/Library/CoreServices/System Events.app   / ��     ' ( ' O   # a ) * ) k   ' ` + +  , - , I  ' ,������
�� .miscactvnull��� ��� null��  ��   -  . / . Z   - O 0 1���� 0 o   - .���� (0 terminalwasrunning terminalWasRunning 1 k   1 K 2 2  3 4 3 l  1 1�� 5 6��   5   create a new window    6 � 7 7 (   c r e a t e   a   n e w   w i n d o w 4  8�� 8 O   1 K 9 : 9 Z   5 J ; <���� ; =  5 > = > = l  5 < ?���� ? I  5 <�� @��
�� .corecnte****       **** @ 2  5 8��
�� 
cwin��  ��  ��   > m   < =����   < I  A F�� A��
�� .coredoscnull��� ��� ctxt A m   A B B B � C C  ��  ��  ��   : m   1 2 D D�                                                                                      @ alis    j  Macintosh HD               ��*H+   .�Terminal.app                                                    .���v�V        ����  	                	Utilities     �
�      �wG�     .� .�
  0Macintosh HD:Applications:Utilities:Terminal.app    T e r m i n a l . a p p    M a c i n t o s h   H D  #Applications/Utilities/Terminal.app   / ��  ��  ��  ��   /  E F E l  P P��������  ��  ��   F  G H G l  P P�� I J��   I * $ execute the command in front window    J � K K H   e x e c u t e   t h e   c o m m a n d   i n   f r o n t   w i n d o w H  L M L I  P ^�� N O
�� .coredoscnull��� ��� ctxt N b   P U P Q P b   P S R S R m   P Q T T � U U 
 c d     " S o   Q R���� 0 cdto cdTo Q m   S T V V � W W  "   ;   c l e a r O �� X��
�� 
kfil X 4  V Z�� Y
�� 
cwin Y m   X Y���� ��   M  Z [ Z l  _ _�� \ ]��   \ > 8 to run gitx (gitk) you can uncomment the following line    ] � ^ ^ p   t o   r u n   g i t x   ( g i t k )   y o u   c a n   u n c o m m e n t   t h e   f o l l o w i n g   l i n e [  _�� _ l  _ _�� ` a��   ` ' ! do script "gitx" in front window    a � b b B   d o   s c r i p t   " g i t x "   i n   f r o n t   w i n d o w��   * m   # $ c c�                                                                                      @ alis    j  Macintosh HD               ��*H+   .�Terminal.app                                                    .���v�V        ����  	                	Utilities     �
�      �wG�     .� .�
  0Macintosh HD:Applications:Utilities:Terminal.app    T e r m i n a l . a p p    M a c i n t o s h   H D  #Applications/Utilities/Terminal.app   / ��   (  d�� d L   b d����  ��  ��       �� e f g������   e ��������
�� .aevtoappnull  �   � ****�� 0 cdto cdTo�� (0 terminalwasrunning terminalWasRunning��   f �� ���� h i��
�� .aevtoappnull  �   � ****�� 0 argv  ��   h ���� 0 argv   i ������ &�� "�� c������ B�� T V��
�� 
cobj�� 0 cdto cdTo�� (0 terminalwasrunning terminalWasRunning
�� 
prcs
�� .coredoexbool       obj 
�� .miscactvnull��� ��� null
�� 
cwin
�� .corecnte****       ****
�� .coredoscnull��� ��� ctxt
�� 
kfil�� e��k/E�OfE�O� *��/j  eE�Y hUO� ;*j O� � *�-j 
j  
�j Y hUY hO��%�%�*�k/l OPUOh g � j j p / U s e r s / s c h i t a l e / w o r k / a p t a n a / g i t / w o r k s p a c e s / m a s t e r / s t u d i o
�� boovtrue��  ascr  ��ޭ