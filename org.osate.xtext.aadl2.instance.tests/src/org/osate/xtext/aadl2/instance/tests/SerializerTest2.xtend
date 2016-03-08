package org.osate.xtext.aadl2.instance.tests

import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.junit.Test
import org.junit.runner.RunWith
import org.osate.aadl2.AadlPackage
import org.osate.xtext.aadl2.instance.InstanceUiInjectorProvider

@RunWith(XtextRunner)
@InjectWith(InstanceUiInjectorProvider)
class SerializerTest2 extends AbstractSerializerTest {
	override getProjectName() {
		"SerializerTest2"
	}
	
	@Test
	def void testEndToEndFlows() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					features
						p1: in data port;
						p2: out data port;
					flows
						f1: flow path p1 -> p2;
				end s1;
				
				system s2
					features
						p3: in data port;
					flows
						f2: flow sink p3;
				end s2;
				
				system s3
					features
						p4: in data port;
						p5: out data port;
					flows
						f3: flow sink p4;
						f4: flow sink p4;
						f5: flow path p4 -> p5;
				end s3;
				
				system implementation s3.i
					subcomponents
						s1_sub: system s1;
						s2_sub: system s2;
					connections
						conn1: port p4 -> s1_sub.p1;
						conn2: port s1_sub.p2 -> s2_sub.p3;
					flows
						f3: flow sink p4 -> conn1 -> s1_sub.f1 -> conn2 -> s2_sub.f2;
						f4: flow sink p4 -> conn1 -> s1_sub -> conn2 -> s2_sub.f2;
						etef1: end to end flow s1_sub -> conn2 -> s2_sub.f2;
				end s3.i;
				
				system s4
					features
						p6: out data port;
						p7: in data port;
					flows
						f6: flow source p6;
						f7: flow sink p7;
				end s4;
				
				system s5
				end s5;
				
				system implementation s5.i
					subcomponents
						s4_sub: system s4;
						s3_sub: system s3.i;
					connections
						conn3: port s4_sub.p6 -> s3_sub.p4;
						conn4: port s3_sub.p5 -> s4_sub.p7;
					flows
						etef2: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f3;
						etef3: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f4;
						etef4: end to end flow s4_sub.f6 -> conn3 -> s3_sub.f5;
						etef5: end to end flow etef4 -> conn4 -> s4_sub.f7;
				end s5.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s5.i", '''
			system s5_i_Instance : pkg1::s5.i {
				system s4_sub [ 0 ] : pkg1::s5.i::s4_sub {
					out dataPort p6 : pkg1::s4::p6 source of ( 1.0 ) destination of ( f6 )
					in dataPort p7 : pkg1::s4::p7 source of ( f7 ) destination of ( 1.1 )
					flow f6 ( -> p6 ) : pkg1::s4::f6
					flow f7 ( p7 -> ) : pkg1::s4::f7
				}
				system s3_sub [ 0 ] : pkg1::s5.i::s3_sub {
					in dataPort p4 : pkg1::s3::p4 source of ( f3 , f4 , f5 )
					out dataPort p5 : pkg1::s3::p5 source of ( 1.1 ) destination of ( f5 )
					system s1_sub [ 0 ] : pkg1::s3.i::s1_sub {
						in dataPort p1 : pkg1::s1::p1 source of ( f1 ) destination of ( 2.0 )
						out dataPort p2 : pkg1::s1::p2 source of ( 1.0 ) destination of ( f1 )
						flow f1 ( p1 -> p2 ) : pkg1::s1::f1
					}
					system s2_sub [ 0 ] : pkg1::s3.i::s2_sub {
						in dataPort p3 : pkg1::s2::p3 source of ( f2 ) destination of ( 1.0 )
						flow f2 ( p3 -> ) : pkg1::s2::f2
					}
					complete portConnection "s1_sub.p2 -> s2_sub.p3" : s1_sub[0].p2 ->
					s2_sub[0].p3 {
						s1_sub[0].p2 -> s2_sub[0].p3 : pkg1::s3.i::conn2 in parent
					}
					flow f3 ( p4 -> ) : pkg1::s3::f3
					flow f4 ( p4 -> ) : pkg1::s3::f4
					flow f5 ( p4 -> p5 ) : pkg1::s3::f5
					end to end flow etef1 s1_sub[0] -> 0 -> s2_sub[0].f2 : pkg1::s3.i::etef1
				}
				complete portConnection "s4_sub.p6 -> s3_sub.s1_sub.p1" : s4_sub[0].p6 ->
				s3_sub[0].s1_sub[0].p1 {
					s4_sub[0].p6 -> s3_sub[0].p4 : pkg1::s5.i::conn3 in parent
					s3_sub[0].p4 -> s3_sub[0].s1_sub[0].p1 : pkg1::s3.i::conn1 in s3_sub[0]
				}
				complete portConnection "s3_sub.p5 -> s4_sub.p7" : s3_sub[0].p5 ->
				s4_sub[0].p7 {
					s3_sub[0].p5 -> s4_sub[0].p7 : pkg1::s5.i::conn4 in parent
				}
				end to end flow etef2 s4_sub[0].f6 -> 0 -> s3_sub[0].s1_sub[0].f1 ->
				s3_sub[0].0 -> s3_sub[0].s2_sub[0].f2 : pkg1::s5.i::etef2
				end to end flow etef3 s4_sub[0].f6 -> 0 -> s3_sub[0].s1_sub[0] -> s3_sub[0].0
				-> s3_sub[0].s2_sub[0].f2 : pkg1::s5.i::etef3
				end to end flow etef4 s4_sub[0].f6 -> 0 -> s3_sub[0].f5 : pkg1::s5.i::etef4
				end to end flow etef5 etef4 -> 1 -> s4_sub[0].f7 : pkg1::s5.i::etef5
				som "No Modes"
			}''')
	}
	
	@Test
	def void testModes() {
		val pkg1FileName = "pkg1.aadl"
		createFiles(pkg1FileName -> '''
			package pkg1
			public
				system s1
					modes
						m1: initial mode;
						m2: mode;
				end s1;
				
				system s2
					requires modes
						m3: initial mode;
						m4: mode;
				end s2;
				
				system s3
					modes
						m5: initial mode;
						m6: mode;
				end s3;
				
				system implementation s3.i
					subcomponents
						s1_sub: system s1;
						s2_sub: system s2;
				end s3.i;
			end pkg1;
		''')
		suppressSerialization
		assertSerialize(testFile(pkg1FileName).resource.contents.head as AadlPackage, "s3.i", '''
			system s3_i_Instance : pkg1::s3.i {
				system s1_sub [ 0 ] : pkg1::s3.i::s1_sub {
					initial mode m1 : pkg1::s1::m1
					mode m2 : pkg1::s1::m2
				}
				system s2_sub [ 0 ] : pkg1::s3.i::s2_sub {
					initial derived mode m3 : pkg1::s2::m3
					derived mode m4 : pkg1::s2::m4
				}
				initial mode m5 : pkg1::s3::m5
				mode m6 : pkg1::s3::m6
				som "m5#s1_sub.m1" m5 , s1_sub[0].m1
				som "m5#s1_sub.m2" m5 , s1_sub[0].m2
				som "m6#s1_sub.m1" m6 , s1_sub[0].m1
				som "m6#s1_sub.m2" m6 , s1_sub[0].m2
			}''')
	}
}