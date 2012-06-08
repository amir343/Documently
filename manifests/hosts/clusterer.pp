node 'clusterer' {
	group { "puppet":
		ensure => "present",
	}

	include supervisor
		supervisor::service {
			'scribe':
				ensure      => present,
					    enable      => true,
					    command     => '/usr/bin/scribed -c /etc/scribe/scribe.conf',
					    environment => 'HADOOP_HOME=/usr/lib/hadoop,LD_LIBRARY_PATH=/usr/lib/jvm/java-6-sun/jre/lib/amd64/server',
					    user        => 'scribe',
					    group       => 'scribe',
					    require     => [ Package['scribe'], User['scribe'] ];
		}
}
